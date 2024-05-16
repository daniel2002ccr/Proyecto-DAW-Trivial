const express = require('express');
const bodyParser = require('body-parser');
const jwt = require('jsonwebtoken');

const app = express();
const secretKey = 'mysecretkey';

app.use(bodyParser.json());

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
    // Verificar las credenciales del usuario
    const { username, password } = req.body;

    if (username === 'root' && password === 'PracticaRoot') {
        const token = jwt.sign({ username }, secretKey);
        res.json({ token });
    } else {
        res.sendStatus(401); // Unauthorized
    }
});

// Ruta protegida que requiere un token válido para acceder
app.get('/admin', verifyToken, (req, res) => {
    res.json({ message: 'Admin area' });
});

// Iniciar el servidor en el puerto 8081
app.listen(8081, () => {
    console.log('Server is running on port 8081');
});
