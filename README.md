# ChatHider
A mod that can hide keywords in blacklist when received chat message

## Supported version
**Minecraft 1.8.9 Forge**

## Todos
- [x] Use character "**'**" insteadof "**`**"
- [ ] Change command usage `/ch convert [string]/'clear` -> `/ch convert <[string]/'clear>`
- [ ] Make the convert string change the length according to the length of the keyword 
- [ ] Match both upper and lower case
- [ ] Fix `/ch convert 'clear` bug
- [ ] Use **`Regex`** and **`Fuzzy matching`** insteadof `Keyword matching`
- [ ] Add `/ch mode <whole/single>` to change shield mode (`whole` for whole chat message, `signle` for signle word)
- [ ] Improve the command processing system
- [ ] Add GUI for better using

## Usage
**Command: `/chathider` or `/ch` for alias**

- **/ch help** » Show mode help page
- **/ch enable** » Temporary enable mod (Restart game expired invalidation)
- **/ch disable** » Temporary disable mod (Same as `/ch enable`)
- **/ch add** ***[keyword]*** » Add a keyword to blacklist
- **/ch remove** ***[keyword]*** » Remove a keyword from blacklist
- **/ch list** » Show all blocked keywords
- **/ch clear** » Clear all keywords from blacklist
- **/ch convert** ***[string]*** **/'clear** » Convert blocked keywords to string or clear string ('clear means it won't show message if contains keywords)

### Examples
`/ch add fuck` » Add `fuck` to blacklist

`/ch remove orphan` » Remove `orphan` from blacklist

`/ch convert *****` » When player received message that contains blocked keywords, it will be converted to `*****`
