# wmappy
Do you ever wonder how list of all countries and cities looks like? Or maybe you wish wikipedia was only for such purpose?  
I have something for you, weirdo

_created for educational purpose_

## how to start?
- `git clone https://github.com/goldenpears/wmappy.git`
- import `wmappy/wmappy` folder via Android Studio (yes, second one)
- run on the emulator or your device in the debug mode

**or** download `.apk` from latest [release](https://github.com/goldenpears/wmappy/releases)

**or** get it at [PlayStore](https://play.google.com/store/apps/details?id=com.locovna.wmappy) and become Alpha tester!

## this is how we do it

- [x] ~~Download a list of countries and cities from [there](https://raw.githubusercontent.com/David-Haim/CountriesToCitiesJSON/master/countriesToCities.json)~~
- [ ] Parse that list into a local database ~~(for now it parses and shows straight into recycle view, right after request via HTTP)~~
- [ ] ~~Create a country selector (dropdown or picker)~~ which is populated from the local database.
- [x] ~~Show a list of cities of the selected country.~~
- [x] ~~Upon selecting a city from the list, fetch info about it and display it on a separate screen. For fetching city info use [Wikipedia search service](http://www.geonames.org/export/wikipedia-webservice.html#wikipediaSearch)~~

## any third-party libraries and frameworks?
[Picasso](http://square.github.io/picasso/) - for load image of `City` at `WikiActivity`. 

Why: unfortunately, at native methods, it would be much junky, so we just use  single line of code for single and simple purpose for keep things clean and readable.

## screenshots
![screenshots](img/wmappy.png)
`splash screen` ⭐️ 
`list of countries` ⭐️ 
`dialog with cities` ⭐️ 
`info about city and photo if exist` ⭐️ 

## formally it is
Example of implementing:
- parsing `JSON` via `HTTP`
- showing result as `recycle view`
- displaying `dialog` using `ArrayList`
- and `explicit intent` to `Activity` with, again, parsed `JSON`
- load image if exist, at `WikiActivity` via `Picasso`
