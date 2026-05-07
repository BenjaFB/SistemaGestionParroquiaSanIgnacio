// @ts-ignore
import { BaileysProvider as Provider } from "@builderbot/provider-baileys";
import { addKeyword, MemoryDB as Database } from "@builderbot/bot";
import { donationFlow, exitFlow, helpFlow } from "./messages.flow.js";

export const guestFlow = addKeyword<Provider, Database>("GUEST").addAnswer(
  `Seleccione una opción:\n\n1. Donación\n2. Dudas y consultas\n3. Salir`,
  { capture: true },
  async (ctx, { gotoFlow, fallBack }) => {
    const opt = (ctx.body || "").trim();

    if (opt === "1") return gotoFlow(donationFlow);
    if (opt === "2") return gotoFlow(helpFlow);
    if (opt === "3") return gotoFlow(exitFlow);

    return fallBack("Seleccione 1, 2 o 3");
  },
);
