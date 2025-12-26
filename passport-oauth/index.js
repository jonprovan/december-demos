import express from 'express';
// must set up sessions when using Passport OAuth by default -- it CAN be set up statelessly with a bunch more config
import session from 'express-session';
import mainRouter from './router/mainRouter.js';
import passportGoogleAuth from './middleware/passportGoogleAuth.js';
import authRouter from './router/authRouter.js';
import passport from 'passport';

// even though you won't get an obvious error about this, you have to import the strategy here BEFORE you have Passport start using it
import './strategy/googleStrategy.js';

const app = express();
app.use(express.json());

// make sure you tell your app to use sessions!
app.use(session({
    secret: 'this_session_key_gets_used_to_generate_a_unique_session',
    resave: false,
    saveUninitialized: false
}));

// we can use this as a fallback if auth doesn't work for some reason
app.get('/error', (req, res) => {
    res.send('If you are seeing this, there has been a problem with authentication.');
})

app.use(passport.initialize());
app.use(passport.session());

app.use('/auth', authRouter());
app.use('/main', mainRouter(passportGoogleAuth));


const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Express is up and listening on port ${PORT}!`);
})