# Back-end documentation
## Summary

- 1: [Endpoints]()
- 2: [Table Relationship]()




## 1: Endpoints



## 2: Table Relationship

##### Last update: 11/01/2023

### Movie table:

| Field  |  Type |Description   |
|---|---|---|
| **IdMovie**  | String  | IMDB Identification number  |
| **Name**  | String  | Movie name  |
| **Year**  | Int  |  Release year |
|  **background** | String  | Movie background image URL  |
|  **Image** | String  |  Movie poster image URL |
|  **Category** | String  | Movie Category  |
|  **Duration** | Double  | Movie duration  |
|  **RateIMDB** | Double  | Movie rate from IMDB  |
| **RateMETA**  | Double  | Movie rate from MetaCritic  |
| **selectDate**   | Date      | Date that the move was consulted|

### User table:

| Field  |  Type |Description   |
|---|---|---|
| **Id**  | Int  | Identification number  |
| **Name** |  String |  User name |
|  **DiscordName** |  String |  Discord name |

### Season table:

| Field  |  Type |Description   |
|---|---|---|
| **Id**  | Int  | Season identification  |
|   **Name**    |String| Season name
| **DateStart** |  String |  Date when the season start |
|  **DateEnd** |  String |  Date when the season ends |

### Avatar table: 
    
| Field  |  Type |Description   |
|---|---|---|
| **Id**  | Int  | Identification Avatar  |
| **FileName** |  String |  Avatar file name  |
|  **fileDownloadUri** |  String |  Path to download avatar file |
|   **fileType**    | String    |   File Type|
|   **size**    | long| Size of the file|

#### Database model

![database](Documentation/Database_image.jpg)


# configs FE

[link for help on extensions](https://itnext.io/keep-code-consistent-across-developers-the-easy-way-with-prettier-eslint-60bb7e91b76c)

[full airbnb style guide](https://github.com/airbnb/javascript)

## extensions

- eslint
- prettier
- simple react snippets or ES7+ React/Redux/React-Native snippets
- path intellisense
- live share (for pair programming)

## general settings

- **semicolon** at the end of every line
- **double** quotes
- use react arrow functional components (shortcut with snippet: `rafce`)
- **organize imports** on autosave (shortcut: `shift + alt + o` or add in settings.json:
```
"editor.codeActionsOnSave": {
    "source.organizeImports": true,
  }
  ```
   
- indent using soft tabs (**2 spaces**)
- `/** ... */` for multiline comments
- `// ` for single line comments
- use `FIXME:` to annotate problems or `TODO:` to annotate solutions to problems
- responsive design
- create top level constants to use within components
- folders named as main component inside of it

