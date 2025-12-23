import { Router } from 'express';

export default function mainRouter(passportStateless) {

    const router = Router();

    router.get('/open', (req, res) => {
        res.send('You have accessed the open endpoint. Bully for you!');
    });

    router.get('/protected', passportStateless, (req, res) => {
        res.send('You have accessed the PROTECTED endpoint. Watch where you step!');
    });

    return router;
}