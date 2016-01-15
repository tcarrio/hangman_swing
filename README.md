# hangman_swing
Hangman Java application for CSE-337 using Java Swing

The website for this project can be found under my <a href="http://github.com/tcarrio">GitHub</a> or at its <a href="http://hangman.carrio.me">webpage</a>

# about
I've developed a habit maintaining all of my code through Github. It allows me to continue utilizing the *git* version control software as well as practice industry standards for code reviewing / merging / etc. This assignment has not been completed yet, however the git history alone tracks back and will abstain me of any potential cheating accusations should any arise. This work has been completed utilizing the integrated Javadocs, the Oracle API for Java 7/8, and various StackOverflow articles. 

# features
<b>Autonomy</b>: Includes a list of over **500 words** of length at least 8 characters up to 16. Hangman will function without internet connectivity with a smaller list of random words to choose from.

<b>Web Connectivity</b>: If network connection is detected, this will implement a RESTful interface to call for a random word. <a href="http://randomword.setgetgo.com/">SetGetGo</a> has a webpage setup that returns a single word upon a GET request that would be useful for this. Allows a specified length (len=[3-20]) as well. 

# design
<b>Programming Style</b>: I followed a loose design scheme, aiming mostly to display my understanding of Object-Oriented Programming through the creation of classes utilizing inheritance and encapsulation. There was a potential for some interesting polymorphic classes to use as a Thread handler, however an easier alternative disposing Threads with the automatic garbage collection provided much cleaner code.
