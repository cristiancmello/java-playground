rm -rf jre
$JAVA_HOME/bin/jlink	\
	--module-path $JAVA_HOME/jmods:out \
	--add-modules academy.learnprogramming.jokeapp \
	--launcher JOKER=academy.learnprogramming.jokeapp/academy.learnprogramming.jokeapp.Main 	\
	--compress 2	\
	--no-header-files	\
	--no-man-pages	\
	--strip-debug	\
	--output jre

