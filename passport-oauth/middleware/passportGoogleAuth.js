// this middleware simply check if the user is authenticated
// if they're not, it redirects them to the Google OAuth flow
export default function passportGoogleAuth(req, res, next) {
    if (req.isAuthenticated && req.isAuthenticated())
        return next();

    // this endpoint will be in our auth controller and will handle the next step in the flow
    res.redirect('/auth/google')
}