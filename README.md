# ChatHider
A mod that can hide keywords in blacklist when received chat message

## Supported version
**Forge 1.8.9**

## Todos
- [ ] Use character "**'**" insteadof "**`**"
- [ ] Match both upper and lower case
- [ ] Fix `/ch conver 'clear` bug
- [ ] Use **`Regex`** and **`Fuzzy matching`** insteadof `Keyword matching`
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
