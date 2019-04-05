
## TV Show search service
A rest service to fetch show by name and number of records to be found.

## Build and Run
You can build and run the project using Gradle
```shell
./gradlew bootRun
```

It will start the application on port 6200 and list of top three shows having House in it's name can be accessed using

```
http://localhost:6200/api/v1/tv-search/shows/q=house&results=3
```

It will print the JSON with following format

```
[
    {
        "name": string, //Name of the show
        "score": double, // score of the show in decimal
        "premiered": string // date when the show was premiered
    },
    ...
]
```

### Improvements
* Test cases are not added at the moment. It can be added to increase the quality of the code and increase the coverage.