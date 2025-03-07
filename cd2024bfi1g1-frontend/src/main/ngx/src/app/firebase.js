
// Este codigo te lo proporciona Firebase en la configuracion general del proyecto
import { initializeApp } from "firebase/app";
import { getMessaging } from "firebase/messaging";

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyAoAsO_Vt28fvZk9mnnCC2H-Q9YlznsGkY",
  authDomain: "coldcarebfi1g1.firebaseapp.com",
  projectId: "coldcarebfi1g1",
  storageBucket: "coldcarebfi1g1.firebasestorage.app",
  messagingSenderId: "1073652342789",
  appId: "1:1073652342789:web:264045a2aa13f63c517c6c"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const messaging = getMessaging(app)
