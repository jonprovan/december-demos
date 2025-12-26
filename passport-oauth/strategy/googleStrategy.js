import { Strategy as GoogleStrategy } from 'passport-google-oauth20';
import passport from 'passport';

// we set up our Google connection information here -- don't publish this part!!
const googleStrategy = new GoogleStrategy({
        scope: ['profile', 'email'],                // what we're requesting from Google about the user
        clientID: '1234567890.apps.googleusercontent.com',
        clientSecret: '1234567890',
        callbackURL: 'http://localhost:3000/auth/google/callback'
    },
    async (accessToken, refreshToken, profile, done) => {
        return done(null, profile)
    }
)

passport.use(googleStrategy);

// in order for sessions to work, we need to be able to serializea and deserialize the user
passport.serializeUser((user, done) => {
    done(null, user);
});

passport.deserializeUser((user, done) => {
    done(null, user);
});

export default googleStrategy;