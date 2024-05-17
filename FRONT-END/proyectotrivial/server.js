const express = require('express');
const bodyParser = require('body-parser');
const jwt = require('jsonwebtoken');
const path = require('path');

const app = express();
const secretKey = 'mysecretkey';

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Middleware para verificar y decodificar el token JWT
function verifyToken(req, res, next) {
    const token = req.headers['authorization'];

    if (typeof token !== 'undefined') {
        jwt.verify(token, secretKey, (err, authData) => {
            if (err) {
                res.sendStatus(403); // Forbidden
            } else {
                req.authData = authData;
                next();
            }
        });
    } else {
        res.sendStatus(403); // Forbidden
    }
}

// Ruta de inicio de sesión para autenticación
app.post('/login', (req, res) => {
    const { username, password } = req.body;

    if (username === 'admin' && password === 'password') {
        const token = jwt.sign({ username }, secretKey);
        res.json({ token });
    } else {
        res.sendStatus(401); // Unauthorized
    }
});

// Ruta protegida que requiere un token válido para acceder
app.get('/admin', verifyToken, (req, res) => {
    res.sendFile(path.join(__dirname, 'admin.html'));
});

// Servir la página de inicio de sesión
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'index.html'));
});

// Iniciar el servidor en el puerto 8081
app.listen(8081, () => {
    console.log('Server is running on port 8081');
});