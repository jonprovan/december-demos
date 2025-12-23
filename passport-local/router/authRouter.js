import db from '../db/db.js';
import bcrypt from 'bcrypt';
import { Router } from 'express';

export default function authRouter() {

    const router = Router();

    router.post('/register', async (req, res) => {

        // extract the username and password from the request body
        const { username, password } = req.body;

        // encrypt the password and save the user to the database
        try {
            // first parameter is the thing to hash, second is the rounds of salt
            const encryptedPassword = await bcrypt.hash(password, 10);

            // write to db
            await db.query('INSERT INTO user (username, password) VALUES(?, ?)', [username, encryptedPassword]);

            // return to the client
            res.json({ message: 'User registration was successful!' });

        } catch (err) {
            res.status(500).json({ message: 'Registration failed. '});
        }

    })

    return router;

}

