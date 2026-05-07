// @ts-ignore
import { BaileysProvider as Provider } from "@builderbot/provider-baileys";
import { addKeyword, MemoryDB as Database } from "@builderbot/bot";
import { donationFlow, exitFlow, helpFlow, volunteerFlow } from "./messages.flow.js";

export const menuFullFlow = addKeyword<Provider, Database>(
  "MENU_FULL",
).addAnswer(
  `Seleccione una opción:\n\n1. Voluntariado\n2. Donación\n3. Dudas y consultas\n4. Salir`,
  { capture: true },
  async (ctx, { gotoFlow, fallBack }) => {
    const opt = (ctx.body || "").trim();

    if (opt === "1") return gotoFlow(volunteerFlow);
    if (opt === "2") return gotoFlow(donationFlow);
    if (opt === "3") return gotoFlow(helpFlow);
    if (opt === "4") return gotoFlow(exitFlow);

    return fallBack("Seleccione 1, 2 o 3");
  },
);
