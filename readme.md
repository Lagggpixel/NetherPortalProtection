# Nether Portal Protection
### Do not ask for support in the reviews. You can find me on my [discord server](discord.gg/y3AmV5jXdf).

# Modifications
Players will be sent to their original portal after a set amount of time in the portal.
If the original portal does not exist, then they will execute certain commands.

# Commands
```
/netherportalprevention reload: Reload plugin configuration files
```

# Permissions
```
netherportalprevention.reload: Access to /netherportalprevention reload command
```

# Configuration
```
# Nether portal protection plugin configuration
# Note that all placeholders from external plugins or placeholder api will work if you have placeholderapi
# installed on your server except any console messages

# Should there be a one block padding created when the player teleports through a nether portal
padding: true

# The delay to teleport players back
# Set this value to -1 to disable
# Set this value above 5, otherwise things will break
delay: 20

# Warnings section when the player is still in the portal.
warnings:
  # The times when the warning is going to be sent.
  times:
    - 20
    - 10
    - 5
    - 3
    - 2
    - 1
  # The warning messages.
  # [action-bar], [chat], [bossbar]
  messages:
    - '[action-bar] &eTeleporting in {time} seconds.'

# Commands to execute if no previous portal is present
# [player], [console]
commands:
  - '[player] spawn'

# Language messages.
lang:
  reload: '&7Reloaded in {time} ms.'
  correct-use: '&7Please use this: &b/netherportalprevention reload'
  not-arg: '&7The argument &c{arg} does not exist or is invalid.'

```