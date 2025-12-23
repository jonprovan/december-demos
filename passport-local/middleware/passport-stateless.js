import passport from 'passport';

const passportStateless = (req, res, next) => {

    // extract the Authorization header information
    const authHeader = req.headers['authorization'];

    // maybe include a check here to see if the header starts with 'Basic ' or something
    // properly encoded basic auth header looks like 'Basic username:password' with the un/pw part Base64 encoded
    const encodedCredentials = authHeader.split(' ')[1];

    // now decode the credentials
    // with the Buffer, decode the value
    // turn it into a String
    // split it at the colon
    // deconstruct the resulting array into username and password
    const [username, password] = Buffer.from(encodedCredentials, 'base64').toString().split(':');

    // add credentials to request body
    // customize this for POST/PUT requests, etc.
    req.body = { username, password };

    // finally, we authenticate using our LocalStrategy
    // three parameters -- strategy, options, callback
    passport.authenticate('local', { session: false }, (err, user, info) => {
        if (!user)
            return res.status(401).json({ message: 'You are not authorized!' });
        req.user = user;
        next();
    })(req, res, next)

}

export default passportStateless;