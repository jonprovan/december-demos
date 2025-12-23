// this file lays out the strategy via which Passport will authenticate our users
// we import the Strategy from the appropriate package, the DB connection info, and the bcrypt functionality
import { Strategy as LocalStrategy } from "passport-local";
import db from '../db/db.js';
import bcrypt from 'bcrypt';

const localStrategy = new LocalStrategy(async (username, password, done) => {

    try {

        // check if the username is in the db
        const [rows] = await db.query('SELECT * FROM user WHERE username = ?', [username]);
        if (rows.length === 0)
            // to get out, return done(<error>, <valid user object or false>, <options like this message>)
            return done(null, false, { message: 'User does not exist!' });

        const user = rows[0];

        // check if the passwords match
        // for bcrypt.compare(), the unencrypted password MUST come first!
        const matches = await bcrypt.compare(password, user.password);

        if (!matches)
            return done(null, false, { message: 'Password does not match!' });

        // once everything is good, return done and include the user
        return done(null, user);

    } catch (err) {
        return done(err);
    }

});

export default localStrategy;