# Flannel Fantasy Bot

A Discord bot for the Flannel Fantasy Discord server

## Setup
Our bot is written in Kotlin using the Javacord library to communicate with Discord.

In order to test the bot yourself, you'll need a bot token, which you can get at the Discord [developer portal](https://discordapp.com/developers/applications/).

You can then invite the bot to a Discord server that you own for testing. 

Once the bot has a home, grab the bot token from the Bot section in the app settings, and add it to your environment as
```bash
export FLANNEL_BOT_TOKEN=YOUR TOKEN HERE
```

You'll also need jdk 12 installed, either Oracle or OpenJDK pick your poison :P

With the jdk install you can build with the provided gradle wrapper with:

```bash
./gradlew :jar
```

and then run the built jar:
```bash
java -jar ./build/libs/bot-0.1.jar
```

## Development
More will be added here as I flesh this out, but I recommend using Intellij as your IDE, as it does simplify a lot of the
aforementioned stuff. The community edition should be fine for this, but I recommend the Professional version (and students can get it for free I believe!)

Entry point for the application (main) is `flannel.FlannelBotApplication`, but the meat and potatoes is `flannel.FlannelClient`

## Commands

Currently there is one command,`%gay` which outputs just how gay it thinks you are at any given time.
Biggest feature planned is role management, allowing users to self-assign non admin roles as they see fit.