import express from 'express';
import mainRouter from './router/mainRouter.js';
import passport from 'passport';
import localStrategy from './passport-strategies/localStrategy.js';
import passportStateless from './middleware/passport-stateless.js';
import authRouter from './router/authRouter.js';

const app = express();
app.use(express.json());

// first, we tell Passport it can use the local strategy
passport.use(localStrategy);

// then, we tell the app to use Passport
app.use(passport.initialize());

// pass the Passport middleware into any router where you'd like to use it
app.use('/main', mainRouter(passportStateless));
app.use('/auth', authRouter());

const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Express app is up...on port ${PORT}!!`);
});