import {
  createBot,
  createProvider,
  createFlow,
} from "@builderbot/bot";
import { MemoryDB as Database } from "@builderbot/bot";
// @ts-ignore
import { BaileysProvider as Provider } from "@builderbot/provider-baileys";
import { welcomeFlow } from "./flows/welcome.flow.js";
import { registeredFlow } from "./flows/registered.flow.js";
import { guestFlow } from "./flows/guest.flow.js";
import { menuFullFlow } from "./flows/menu.flow.js";
import { donationFlow, exitFlow, helpFlow, volunteerFlow } from "./flows/messages.flow.js";

const PORT = process.env.PORT ?? 3008;





const main = async () => {
  const adapterFlow = createFlow([
    welcomeFlow,
    registeredFlow,
    guestFlow,
    menuFullFlow,
    donationFlow,
    volunteerFlow,
    helpFlow,
    exitFlow,
  ]);

  // If you experience ERRO AUTH issues, check the latest WhatsApp version at:
  // https://wppconnect.io/whatsapp-versions/
  // Example: version "2.3000.1035824857-alpha" -> [2, 3000, 1035824857]
  const adapterProvider = createProvider(Provider, {
    version: [2, 3000, 1035824857],
  });
  const adapterDB = new Database();

  const { handleCtx, httpServer } = await createBot({
    flow: adapterFlow,
    provider: adapterProvider,
    database: adapterDB,
  });

  adapterProvider.server.post(
    "/v1/messages",
    handleCtx(async (bot, req, res) => {
      const { number, message, urlMedia } = req.body;
      await bot.sendMessage(number, message, { media: urlMedia ?? null });
      return res.end("sended");
    }),
  );

  adapterProvider.server.post(
    "/v1/register",
    handleCtx(async (bot, req, res) => {
      const { number, name } = req.body;
      await bot.dispatch("REGISTER_FLOW", { from: number, name });
      return res.end("trigger");
    }),
  );

  adapterProvider.server.post(
    "/v1/samples",
    handleCtx(async (bot, req, res) => {
      const { number, name } = req.body;
      await bot.dispatch("SAMPLES", { from: number, name });
      return res.end("trigger");
    }),
  );

  adapterProvider.server.post(
    "/v1/blacklist",
    handleCtx(async (bot, req, res) => {
      const { number, intent } = req.body;
      if (intent === "remove") bot.blacklist.remove(number);
      if (intent === "add") bot.blacklist.add(number);

      res.writeHead(200, { "Content-Type": "application/json" });
      return res.end(JSON.stringify({ status: "ok", number, intent }));
    }),
  );

  adapterProvider.server.get(
    "/v1/blacklist/list",
    handleCtx(async (bot, req, res) => {
      const blacklist = bot.blacklist.getList();
      res.writeHead(200, { "Content-Type": "application/json" });
      return res.end(JSON.stringify({ status: "ok", blacklist }));
    }),
  );

  httpServer(+PORT);
};

main();
