const express = require('express');
const http = require('http');
const { PeerServer } = require('peer');

const app = express();
const PORT = process.env.PORT || 3000;

// Раздаём статические файлы из папки public
app.use(express.static('public'));

// Создаём HTTP-сервер
const server = http.createServer(app);

// PeerJS сервер на том же порту, но на пути /privatvoice
const peerServer = PeerServer({
    port: PORT,
    path: '/privatvoice',
    proxied: true
});

// Запускаем всё на одном порту
server.on('request', app);
server.listen(PORT, () => {
    console.log(`✅ Сервер запущен на порту ${PORT}`);
    console.log(`🌐 Приложение: http://localhost:${PORT}`);
});