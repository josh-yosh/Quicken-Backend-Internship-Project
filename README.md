# Simple Aggregation Model 

Hello! Welcome to my simple model to represent accounts and their transactions.

## Planning and System Design

<img width="1476" height="652" alt="Screenshot 2025-12-13 at 17 57 25" src="https://github.com/user-attachments/assets/f1b9edc1-adc0-4db9-8fcf-fc0a392317f2" />
Design is rudimentary and is prone to change and improvements. It is the current design of the most updated project.

[Figma Link](https://www.figma.com/design/akU3wrgITRwjBbe98IzwQJ/Untitled?node-id=0-1&t=SCIhLiJu3h6eJpJY-1)

## Running the program through docker
After forking or downloading files. Change directory into the file aggregation-model
```
cd aggregation-model
```

To start the program through docker, run the following command
```
docker compose up --build
```

## Running the program without docker
After forking or downloading files. Change directory into the file aggregation-model
```
cd aggregation-model
```

Build gradle file

```
gradle build
```

Run Springboot project using gradle and its bootrun command

```
gradle bootrun
```
## Api Routes

To get all accounts. Accounts will have and id, a name, and a description.
```
/api/accounts
```

To get a Summary for a specific account within a range of dates. Summaries will contain incomes, expenses, a net amount, and a pair of dates
```
/api/accounts/{accountId}/summary?from=YYYY-MM-DD&to=YYYY-MM-DD
```

To get a list of daily Summaries for a specific account within a range of dates. Summaries will contain incomes, expenses, a net amount, and a date.
```
/api/accounts/{accountId}/daily-summary?from=YYYY-MM-DD&to=YYYY-MM-DD
```


## Testing through postman

[Postman test collections](https://joshuayoshioka-6596360.postman.co/workspace/Joshua-Yoshioka's-Workspace~380f6ff8-d6bc-48df-83f9-ceeefaece440/collection/50751831-60133920-2694-4fbb-97e3-d9ff69967aa2?action=share&source=copy-link&creator=50751831)
