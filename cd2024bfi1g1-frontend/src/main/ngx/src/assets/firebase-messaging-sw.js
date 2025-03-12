// Este es el service worker que escuchara aunq nuestra aplicaion este cerrada

importScripts("https://www.gstatic.com/firebasejs/11.4.0/firebase-app-compat.js");
importScripts("https://www.gstatic.com/firebasejs/11.4.0/firebase-messaging-compat.js");

// Es el mismo que el de firebase.js
const firebaseConfig = {
  apiKey: "AIzaSyAoAsO_Vt28fvZk9mnnCC2H-Q9YlznsGkY",
  authDomain: "coldcarebfi1g1.firebaseapp.com",
  projectId: "coldcarebfi1g1",
  storageBucket: "coldcarebfi1g1.firebasestorage.app",
  messagingSenderId: "1073652342789",
  appId: "1:1073652342789:web:264045a2aa13f63c517c6c"
};

firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();

// Objeto para almacenar las traducciones cargadas
let translations = {};

/**
 * Carga el archivo de traducción según el idioma especificado.
 */
async function loadTranslations(language) {
  try {
    const response = await fetch(`/assets/i18n/${language}.json`);
    console.log(response);
    translations = await response.json();
  } catch (error) {
    console.error("Error al cargar traducciones:", error);
  }
}


// Retorna la traducción para una clave dada.
function translate(key) {
  return translations[key] || key;
}

// Escucha los mensajes en background
messaging.onBackgroundMessage(async (payload) => {
  console.log("Mensaje en background recibido:", payload);
  console.log("mensaje de test")

  // // Se espera que el payload incluya data.language (por ejemplo, "es" o "en")
  const language = payload.data?.language || "en";
  await loadTranslations(language);

  // // Se asumen que los valores en notification son claves de traducción
  // const titleKey = payload.notification.title;
  // const bodyKey = payload.notification.body;

  // const translatedTitle = translate(titleKey);
  // const translatedBody = translate(bodyKey);

  const notificationOptions = {
    body: "translatedBody",
    icon: "./icons/ontimize32.png"
  };

  // console.log(translatedTitle, translatedBody);
  console.log(this, self)
  console.log(self.ServiceWorkerRegistration)
  console.log(self.registration)
  await self.registration.showNotification("translatedTitle", notificationOptions);
});