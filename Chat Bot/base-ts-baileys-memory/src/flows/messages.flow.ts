// @ts-ignore
import { BaileysProvider as Provider } from "@builderbot/provider-baileys";
import { addKeyword, MemoryDB as Database } from "@builderbot/bot";

export const donationFlow = addKeyword<Provider, Database>(
  "DONATION",
).addAnswer(
  "Por favor rellene el formulario:\n👉 https://www.figma.com/make/N3TN0NGzdllvaM53dmyuv6/Página-web-de-donaciones?t=668lnNvQWvBlGxkb-1",
);

export const volunteerFlow = addKeyword<Provider, Database>("VOLUNTEER")
  .addAnswer(
    "Complete este formulario https://www.figma.com/make/eJnCccJmyBExouFwODUazY/Social-Feed-App-Design?t=668lnNvQWvBlGxkb-1 y lo contactaremos cuando haya eventos disponibles.",
  )

export const helpFlow = addKeyword<Provider, Database>("HELP").addAnswer(
  "Un administrador se comunicará contigo en breve por WhatsApp.",
);

export const exitFlow = addKeyword<Provider, Database>("EXIT").addAnswer(
  "Gracias por interesarte en nuestra causa 🙌",
);
