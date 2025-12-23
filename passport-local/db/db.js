import mysql2 from 'mysql2/promise';

// setting up DB connection information
const db = mysql2.createPool({
    host: 'localhost',
    user: 'root',
    password: 'root',
    database: 'passportlocal',
    port: 3306
});

export default db;