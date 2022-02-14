<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<head> <meta charset="UTF-8">
    <title>WebSocket</title>
</head>
<body>
<h1>WebSocket Chat Test</h1>
<div>
    <input type="text" id="msg" class="form-contorl" value="Input Value"/>
    <button id="btnSend" class="btn btn-primary"> Send Message </button>
</div>

<button onclick="connect()">Connect</button>

</body>
</html>

<script>
    var socket = null;
    $(document).ready( function() {
        connect();
        $('#btnSend').on('click', function(event){
            event.preventDefault();
            if(socket.readyState !== 1) return;
            let msg = $('input#msg').val();
            socket.send(msg);
        });
    });

    function connect(){
        var ws = new SockJS("/ws/chat");
        socket = ws;
        ws.onopen = function() {
            console.log('Info : connection opened');
        }

        ws.onmessage = function (event) {
            console.log("Received Message : ", event.data + '\n');
        }

        ws.onclose = function(event) {
            console.log('Info : connection closed');
        }

        ws.onerror = function(err) {
            console.log('Error : ' + err);
        }
    }
</script>