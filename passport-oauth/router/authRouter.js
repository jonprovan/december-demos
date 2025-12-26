import express from 'express';
import passport from 'passport';

export default function authRouter() {
    const router = express.Router();

    // these two routes are required for this flow to work
    router.get('/google', passport.authenticate('google', { scope: ['profile', 'email'] }));

    router.get('/google/callback', passport.authenticate(
        'google',                                           // strategy
        { failureRedirect: '/error' }),                      // where to go if there's an auth error
        (req, res) => { res.redirect('/main/loggedin') }    // what to do if they're successful at logging in
    );

    return router;
}