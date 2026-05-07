import { addKeyword } from "@builderbot/bot";
// @ts-ignore
import { BaileysProvider as Provider } from "@builderbot/provider-baileys";
import { MemoryDB as Database } from "@builderbot/bot";
import { registeredFlow } from "./registered.flow.js";
import { guestFlow } from "./guest.flow.js";

export const welcomeFlow = addKeyword<Provider, Database>(["hola"]).addAnswer(
  "Hola 👋 Bienvenido a la fundación\n\n¿Es usted un usuario registrado?\n\nResponda: *si* o *no*",
  { capture: true },
  async (ctx, { gotoFlow, fallBack }) => {
    const msg = (ctx.body || "").toLowerCase();

    if (msg === "si") return gotoFlow(registeredFlow);
    if (msg === "no") return gotoFlow(guestFlow);

    return fallBack("Responda *si* o *no*");
  },
);
