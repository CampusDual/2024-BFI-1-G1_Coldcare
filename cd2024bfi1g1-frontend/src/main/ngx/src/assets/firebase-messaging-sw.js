// Este es el service worker que escuchara aunq nuestra aplicaion este cerrada

importScripts("https://www.gstatic.com/firebasejs/11.4.0/firebase-app-compat.js")
importScripts("https://www.gstatic.com/firebasejs/11.4.0/firebase-messaging-compat.js")

// Es el mismo que el de firebase.js
const firebaseConfig = {
  apiKey: "AIzaSyAoAsO_Vt28fvZk9mnnCC2H-Q9YlznsGkY",
  authDomain: "coldcarebfi1g1.firebaseapp.com",
  projectId: "coldcarebfi1g1",
  storageBucket: "coldcarebfi1g1.firebasestorage.app",
  messagingSenderId: "1073652342789",
  appId: "1:1073652342789:web:264045a2aa13f63c517c6c"
};

const app = firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging(app)

//Si recibimos un mensaje mientras estamos ausentes
messaging.onBackgroundMessage(payload => {
  console.log("Recibiste mensaje mientras estabas ausente");

  //previo a mostrar notificacion
  const notificationTitle = payload.notification.title;
  const nofificationOptions = {
    body: payload.notification.body,
    icon: "./icons/ontimize32.png"
  }

  return self.ServiceWorkerRegistration.showNotification(
    notificationTitle,
    nofificationOptions
  )
  
})