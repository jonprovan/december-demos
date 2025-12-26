import express from 'express';

export default function mainRouter(authMiddleware) {
    const router = express.Router();

    router.get('/open', (req, res) => {
        res.send('This endpoint is open without authentication.');
    })

    router.get('/loggedin', authMiddleware, (req, res) => {

        // the req.user property now contains limited details about the user provided by Google
        // you can use them to link to records in your own database, limit actions based on role, etc.
        res.send(`Hello, ${req.user._json.email} -- you have successfully logged in!`);
    })

    return router;
}