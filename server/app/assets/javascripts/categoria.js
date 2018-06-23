(function () {
  "use strict";

  let getPosicionMouse = function(canvas, event) {
    let rect = canvas.getBoundingClientRect();
    let scaleX = canvas.width / rect.width;
    let scaleY = canvas.height / rect.height;
    return {
      x: (event.clientX - rect.left) * scaleX,
      y: (event.clientY - rect.top) * scaleY,
    };
  };

  let dibujarPunto = function(contexto, x, y) {
    const RADIO_1 = 30;
    const RADIO_2 = 20;
    const COLOR_1 = "#ff0000";
    const COLOR_2 = "#0000ff";

    contexto.fillStyle = COLOR_1;
    contexto.beginPath();
    contexto.arc(x, y, RADIO_1, 0, Math.PI * 2);
    contexto.fill();

    contexto.fillStyle = COLOR_2;
    contexto.beginPath();
    contexto.arc(x, y, RADIO_2, 0, Math.PI * 2);
    contexto.fill();
  };

  window.cargarSeleccionadorPunto = function(canvas, plano, inputX, inputY, botonQuitar) {
    canvas.setAttribute("width", plano.width);
    canvas.setAttribute("height", plano.height);
    let contexto = canvas.getContext("2d");

    contexto.drawImage(plano, 0, 0);

    if (inputX.value && inputX.value !== "") {
      dibujarPunto(contexto, +inputX.value, +inputY.value);
    }

    canvas.onclick = function (event) {
      contexto.drawImage(plano, 0, 0);

      let coordenadas = getPosicionMouse(canvas, event);
      dibujarPunto(contexto, coordenadas.x, coordenadas.y);

      inputX.value = coordenadas.x;
      inputY.value = coordenadas.y;
    };

    botonQuitar.onclick = function () {
      contexto.drawImage(plano, 0, 0);
      inputX.value = "";
      inputY.value = "";
    };
  };
})();
