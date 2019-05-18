```

find . -type f -print0 | xargs -0 -I % perl -pi -e 's/RedReader/BlueReader/g' %

find  . -iname "RedReader*java"

mv ./org/quantumbadger/bluereader/RedReader.java ./org/quantumbadger/bluereader/BlueReader.java

```

