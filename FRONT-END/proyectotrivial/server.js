const express = require('express');
const bodyParser = require('body-parser');
const jwt = require('jsonwebtoken');
const path = require('path');
const cors = require('cors');

const app = express();
const secretKey = 'mysecretkey';

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(cors());
app.use(express.static(path.join(__dirname, 'public')));

function verifyToken(req, res, next) {
  const token = req.headers['authorization'];

  if (token) {
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

    if (username === 'hola' && password === '1234') {
        const token = jwt.sign({ username }, secretKey, { expiresIn: '1h' });
        res.json({ token });
    } else {
        res.sendStatus(401); // Unauthorized
    }
});


app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

app.listen(8081, () => {
  console.log('Server is running on port 8081');
});
