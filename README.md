# Music-Database-System, Spring 2017
This is a the final group project for my Spring 2017 Database Management Systems class. It is a music database that allows users to create and manage playlists.

This was the first group coding project I ever participated in. We started with five members, but two dropped out and I took over most of their responsibilities.

Full details for the assignment are available in DatabaseFinalProjectAssignment.pdf

Database Project Proposal.pdf is our original proposal for our project.

MusicDatabasePresentation.pdf is the slides for the presentation we gave in front of the class on our project.

Music Database System Final Report.pdf is the final report that we turned in, with a detailed explanation of our system (though not the code).

We used a sqlite database for this system.

Most of the GUI was created using WindowBuilder in Eclipse.

Modules I wrote:
DBIO.java, a program that preforms most of the required database IO operations for the project.
InsertWNDW.java, the window for adding new songs to the database.
SearchWindow.java, the window for searching the database and deleting songs.

Other Modules:
LoginDriver.java, the login window and starting point of the program.
MainWindow.java, the main window of the program where playlists are displayed
MusicDatabase.java, utilities for the MainWindow GUI and playlist management
Playlist.java, a class for creating playlist objects
PlaylistEditor.java, a window for editing playlists,
PlaylistWriter.java, writes playlists to text files
Song.java, a class for creating song objects.
UpdateWindow.java, a window to update songs.
