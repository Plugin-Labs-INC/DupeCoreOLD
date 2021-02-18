# DupeCore 

The all in one for dupe/random item servers

------

## Contributors

[Reassembly](https://github.com/TrueReassembly), [ReportCardsMC](https://github.com/ReportCardsMC)

## Commands

- /dupe

  *Description*: Duplicate the item in your hand.

  *Aliases*: none

  *Usage*: /dupe

- /toggleitems

  *Description*: Toggle on/off obtaining random items

  *Aliases*: /ti, /titmes, /togglei

  *Usage*: /toggleitems

- /dupecore

  *Description*: Get information on DupeCore and reload the config

  *Aliases*: /dcore

  *Usage*: /dupecore [reload]

## Config

 » **Plugin Messages**

- pluginPrefix - Prefix of command messages

  

- dupeMessage - Message when you dupe an item

- dupeNoItemMessage - Message when you try to dupe nothing

- toggleRandomItemOn - Message when you toggle random items on

- toggleRandomItemOff - Message when you toggle random items on

- inventoryFullMessage - Message when your inventory is full and the random items tries to give an item

 » **Dupe Item Configuration**

- blockedDupeItems - List of items that you can't dupe (Uses this [list](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html))

 » **Random Item Configuration**

- doRandomItems - Whether or not random items is enabled
- randomItemInterval - Seconds between each random item
- giveSameRandomItem - If enabled it gives everyone the same item instead of each person getting a random item
- inventoryFullNotification - Whether or not to send the inventory full action bar
- blockedRandomItems - List of items that are disabled from obtaining from random items

 » **Miscellaneous Configuration**

- disableSpawnEggs - Blocks spawn eggs from being used

