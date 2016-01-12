# hangman_prelim
Preliminary Hangman Java application for CSE-337 using Java Swing

# features
<b>Autonomy</b>: Includes a list of over 100 words of length less than or equal to 16 characters. Hangman will function without internet connectivity with a smaller list of random words to choose from.

<b>Web Connectivity</b>: If network connection is detected, this will implement a RESTful interface to call for a random word. <a href="http://randomword.setgetgo.com/">SetGetGo</a> has a webpage setup that returns a single word upon a GET request that would be useful for this. Allows a specified length (len=[3-20]) as well. 
