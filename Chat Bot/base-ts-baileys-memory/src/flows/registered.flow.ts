// @ts-ignore
import { BaileysProvider as Provider } from "@builderbot/provider-baileys";
import { addKeyword, MemoryDB as Database } from "@builderbot/bot";
import { menuFullFlow } from "./menu.flow.js";

type UserState = {
  name?: string;
  rut?: string;
  password?: string;
};

export const registeredFlow = addKeyword<Provider, Database>("REGISTER")
  .addAnswer(
    "Ingrese su nombre:",
    { capture: true },
    async (ctx, { state }) => {
      await state.update({ name: ctx.body } as UserState);
    },
  )
  .addAnswer("Ingrese su RUT:", { capture: true }, async (ctx, { state }) => {
    await state.update({ rut: ctx.body } as UserState);
  })
  .addAnswer(
    "Ingrese su contraseña:",
    { capture: true },
    async (ctx, { state, gotoFlow }) => {
      await state.update({ password: ctx.body } as UserState);
      return gotoFlow(menuFullFlow);
    },
  )
  .addAction(async (ctx, { state }) => {
    console.log("state from registration", {
      name: state.get("name"),
      rut: state.get("rut"),
      password: state.get("password"),
    });
  });
