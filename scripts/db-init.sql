-- TODO: Add your database init script here. This should initialize all your tables, and add any initial data required.
DROP TABLE IF EXISTS blogging_comment;
DROP TABLE IF EXISTS bloggingDB_article;
DROP TABLE IF EXISTS bloggingDB_userLogin;

-- one is for the user information page
-- it can be used to verify the passwords

CREATE TABLE bloggingDB_userLogin
(
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(20) UNIQUE NOT NULL,
    hashcode VARCHAR(600) NOT NULL,
    salt VARCHAR(600) NOT NULL,
    firstName VARCHAR(20) NOT NULL,
    lastName  VARCHAR(20) NOT NULL,
    dateOfBirth VARCHAR(10) NOT NULL,
    selfIntroduction TEXT,
    avatarImage VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

-- one is for the user information page

CREATE TABLE bloggingDB_article(
    id INT NOT NULL AUTO_INCREMENT,
    dateOfEntry TEXT NOT NULL DEFAULT DATE_ADD(DATE_FORMAT(CURDATE(),'%Y/%m/%d'), INTERVAL 12 Hour),
    timeOfEntry TEXT NOT NULL DEFAULT DATE_SUB(DATE_FORMAT(CURRENT_TIMESTAMP(), '%Y/%m/%d %T'), INTERVAL -12 Hour),
    text TEXT NOT NULL,
    title TEXT NOT NULL,
    imageFile VARCHAR(100),
    authorId INT NOT NULL,
    username VARCHAR(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (authorId) REFERENCES bloggingDB_userLogin(id)
);


CREATE TABLE blogging_comment(
    id INT NOT NULL AUTO_INCREMENT,
    text TEXT,
    time TEXT NOT NULL DEFAULT DATE_SUB(DATE_FORMAT(CURRENT_TIMESTAMP(), '%Y/%m/%d %T'), INTERVAL -12 HOUR),
    username VARCHAR(20) NOT NULL,
    userId INT NOT NULL ,
    articleId INT NOT NULL ,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES bloggingDB_userLogin(id),
    FOREIGN KEY (articleId) REFERENCES bloggingDB_article(id)
);

INSERT INTO bloggingDB_userLogin(username,hashcode,salt,firstName,lastName,dateOfBirth,selfIntroduction,avatarImage)
VALUES ('Testuser1','12345689','987654321','John','Smith','01-01-2000','I am a human man and definately not a bunch of Possums in a trench coat','web/images/Default.png'),
('Testuser2','abcdefg','15651665165','Jane','Smith','02-01-2001','I am a human woman and definately not a bunch of Possums in a trench coat','web/images/Default.png'),
('Testuser3','a2b3c4f5s5','jdndfj131','Joey','Smith','01-01-2010','I am a human boy and definately not a bunch of Possums in a trench coat','web/images/Default.png'),
('Testuser4','1d5s2f5s1d','ksdnjnj555','Mary','Smith','01-01-2012','I am a human girl and definately not a bunch of Possums in a trench coat','web/images/Default.png');

INSERT INTO bloggingDB_article( text, title, imageFile, authorId, username) VALUES
('asdfqwe','saqwe','Lion.png',1,'Testuser1'),
('Once upon time there was a human, who was in fact not a human. So I really just told a lie, which you probably believed for a split second. Did you enjoy my gibberish? Am I at 100 characters yet? Just kidding I was wanting to see what 500 characters looked like in the browser. It will be very interesting to find out. Perhaps I should just copy and paste in Dolores Ipsum text. Teeeeeeeeeeeeeeeeeexxxxxxttttttttttttttttttt

Behind sooner dining so window excuse he summer. Breakfast met certainty and fulfilled propriety led. Waited get either are wooded little her. Contrasted unreserved as mr particular collecting it everything as indulgence. Seems ask meant merry could put. Age old begin had boy noisy table front whole given.

Exquisite cordially mr happiness of neglected distrusts. Boisterous impossible unaffected he me everything. Is fine loud deal an rent open give. Find upon and sent spot song son eyes. Do endeavor he differed carriage is learning my graceful. Feel plan know is he like on pure. See burst found sir met think hopes are marry among. Delightful remarkably new assistance saw literature mrs favourable.

On on produce colonel pointed. Just four sold need over how any. In to september suspicion determine he prevailed admitting. On adapted an as affixed limited on. Giving cousin warmly things no spring mr be abroad. Relation breeding be as repeated strictly followed margaret. One gravity son brought shyness waiting regular led ham.','Random website gibberish','Monkey.PNG',1,'Testuser1'),
('Arrived totally in as between private. Favour of so as on pretty though elinor direct. Reasonable estimating be alteration we themselves entreaties me of reasonably. Direct wished so be expect polite valley. Whose asked stand it sense no spoil to. Prudent you too his conduct feeling limited and. Side he lose paid as hope so face upon be. Goodness did suitable learning put.

Are own design entire former get should. Advantages boisterous day excellence boy. Out between our two waiting wishing. Pursuit he he garrets greater towards amiable so placing. Nothing off how norland delight. Abode shy shade she hours forth its use. Up whole of fancy ye quiet do. Justice fortune no to is if winding morning forming.

Remember outweigh do he desirous no cheerful. Do of doors water ye guest. We if prosperous comparison middletons at. Park we in lose like at no. An so to preferred convinced distrusts he determine. In musical me my placing clothes comfort pleased hearing. Any residence you satisfied and rapturous certainty two. Procured outweigh as outlived so so. On in bringing graceful proposal blessing of marriage outlived. Son rent face our loud near.

On projection apartments unsatiable so if he entreaties appearance. Rose you wife how set lady half wish. Hard sing an in true felt. Welcomed stronger if steepest ecstatic an suitable finished of oh. Entered at excited at forming between so produce. Chicken unknown besides attacks gay compact out you. Continuing no simplicity no favourable on reasonably melancholy estimating. Own hence views two ask right whole ten seems. What near kept met call old west dine. Our announcing sufficient why pianoforte. ','Gibberish text','Dragonite.png',2,'Testuser2'),
('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Suspendisse ultrices gravida dictum fusce ut placerat. Neque volutpat ac tincidunt vitae semper quis lectus. Magna etiam tempor orci eu. Aliquet lectus proin nibh nisl condimentum. Quis viverra nibh cras pulvinar mattis nunc sed blandit libero. Metus dictum at tempor commodo ullamcorper a lacus vestibulum sed. Mauris in aliquam sem fringilla ut morbi tincidunt augue interdum. Faucibus nisl tincidunt eget nullam non. Consectetur adipiscing elit duis tristique sollicitudin nibh. Mollis aliquam ut porttitor leo a. Duis tristique sollicitudin nibh sit. Gravida neque convallis a cras semper auctor.

Cras pulvinar mattis nunc sed blandit libero volutpat sed. Pharetra vel turpis nunc eget lorem dolor sed viverra ipsum. Libero justo laoreet sit amet cursus sit. Risus pretium quam vulputate dignissim. Cursus euismod quis viverra nibh cras pulvinar mattis nunc sed. Sed libero enim sed faucibus turpis. Enim sit amet venenatis urna cursus eget. Id nibh tortor id aliquet lectus proin. Arcu ac tortor dignissim convallis. Elementum facilisis leo vel fringilla est ullamcorper eget. Dictum non consectetur a erat nam at lectus urna. Leo in vitae turpis massa sed elementum tempus. Tempus urna et pharetra pharetra. Sed libero enim sed faucibus. Vestibulum sed arcu non odio euismod lacinia at quis risus. Vitae sapien pellentesque habitant morbi tristique senectus et netus et.','Fantastic blog title','RedHead.PNG',4,'Testuser4'),
('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Suspendisse ultrices gravida dictum fusce ut placerat. Neque volutpat ac tincidunt vitae semper quis lectus. Magna etiam tempor orci eu. Aliquet lectus proin nibh nisl condimentum. Quis viverra nibh cras pulvinar mattis nunc sed blandit libero. Metus dictum at tempor commodo ullamcorper a lacus vestibulum sed. Mauris in aliquam sem fringilla ut morbi tincidunt augue interdum. Faucibus nisl tincidunt eget nullam non. Consectetur adipiscing elit duis tristique sollicitudin nibh. Mollis aliquam ut porttitor leo a. Duis tristique sollicitudin nibh sit. Gravida neque convallis a cras semper auctor.

Cras pulvinar mattis nunc sed blandit libero volutpat sed. Pharetra vel turpis nunc eget lorem dolor sed viverra ipsum. Libero justo laoreet sit amet cursus sit. Risus pretium quam vulputate dignissim. Cursus euismod quis viverra nibh cras pulvinar mattis nunc sed. Sed libero enim sed faucibus turpis. Enim sit amet venenatis urna cursus eget. Id nibh tortor id aliquet lectus proin. Arcu ac tortor dignissim convallis. Elementum facilisis leo vel fringilla est ullamcorper eget. Dictum non consectetur a erat nam at lectus urna. Leo in vitae turpis massa sed elementum tempus. Tempus urna et pharetra pharetra. Sed libero enim sed faucibus. Vestibulum sed arcu non odio euismod lacinia at quis risus. Vitae sapien pellentesque habitant morbi tristique senectus et netus et.','2 x Doloreses','Seal.png',2,'Testuser2'),
('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ornare aenean euismod elementum nisi. Ac turpis egestas sed tempus urna et pharetra. Odio ut sem nulla pharetra diam. Orci nulla pellentesque dignissim enim sit. Eget gravida cum sociis natoque penatibus et. Augue mauris augue neque gravida in fermentum et sollicitudin. Leo integer malesuada nunc vel risus commodo. Ornare quam viverra orci sagittis eu volutpat odio facilisis. Libero nunc consequat interdum varius sit. Urna molestie at elementum eu facilisis sed odio morbi quis. Id diam vel quam elementum pulvinar etiam non quam. A pellentesque sit amet porttitor eget dolor morbi non.

Ipsum suspendisse ultrices gravida dictum fusce ut placerat orci nulla. Sagittis orci a scelerisque purus semper eget. Nulla aliquet porttitor lacus luctus accumsan tortor posuere. Vestibulum morbi blandit cursus risus at ultrices mi. Vestibulum lorem sed risus ultricies tristique nulla aliquet. Augue mauris augue neque gravida in. Id diam maecenas ultricies mi eget mauris pharetra et ultrices. Hendrerit gravida rutrum quisque non tellus orci. Mauris nunc congue nisi vitae suscipit. Nullam vehicula ipsum a arcu cursus vitae. Dictum fusce ut placerat orci nulla pellentesque dignissim. Cursus mattis molestie a iaculis at erat pellentesque adipiscing. Quam elementum pulvinar etiam non quam lacus suspendisse faucibus interdum. Aliquam ultrices sagittis orci a. Cras tincidunt lobortis feugiat vivamus.

Nunc faucibus a pellentesque sit. Egestas integer eget aliquet nibh. Felis eget velit aliquet sagittis id consectetur. In hac habitasse platea dictumst vestibulum rhoncus est. Faucibus in ornare quam viverra orci sagittis eu volutpat odio. Neque convallis a cras semper auctor. Leo urna molestie at elementum. Tincidunt eget nullam non nisi est sit. Blandit libero volutpat sed cras ornare arcu. Ligula ullamcorper malesuada proin libero. Convallis posuere morbi leo urna molestie at elementum eu facilisis. Diam ut venenatis tellus in metus vulputate eu scelerisque. Eleifend donec pretium vulputate sapien. Sapien pellentesque habitant morbi tristique senectus et netus et. Integer quis auctor elit sed vulputate.

Mi eget mauris pharetra et ultrices neque. Tempus quam pellentesque nec nam aliquam sem et. Nisl rhoncus mattis rhoncus urna neque viverra. Porttitor eget dolor morbi non arcu risus quis varius. Imperdiet nulla malesuada pellentesque elit eget gravida cum sociis natoque. Et malesuada fames ac turpis egestas maecenas pharetra convallis posuere. Fermentum odio eu feugiat pretium nibh. Placerat in egestas erat imperdiet sed. Integer eget aliquet nibh praesent tristique. Ultricies leo integer malesuada nunc vel risus commodo. Sem et tortor consequat id. Pharetra magna ac placerat vestibulum lectus mauris ultrices eros in. Sodales ut eu sem integer vitae justo. Arcu risus quis varius quam quisque id. Amet consectetur adipiscing elit pellentesque. Amet dictum sit amet justo. Luctus venenatis lectus magna fringilla urna.

Enim ut sem viverra aliquet eget sit amet. Est ultricies integer quis auctor elit sed vulputate. Sed viverra ipsum nunc aliquet. Massa massa ultricies mi quis hendrerit. Odio facilisis mauris sit amet massa vitae. Interdum consectetur libero id faucibus. Id aliquet lectus proin nibh nisl condimentum id. In eu mi bibendum neque egestas. Justo nec ultrices dui sapien eget. Bibendum ut tristique et egestas quis ipsum. Integer feugiat scelerisque varius morbi enim. Eu facilisis sed odio morbi quis commodo odio aenean sed. Amet nisl suscipit adipiscing bibendum est ultricies integer quis. Sem nulla pharetra diam sit. Scelerisque fermentum dui faucibus in. Cras semper auctor neque vitae tempus quam pellentesque nec nam. Sapien eget mi proin sed libero. Egestas congue quisque egestas diam. Mattis rhoncus urna neque viverra justo. Ut lectus arcu bibendum at varius vel pharetra vel.','Dolores Ipsum','Sloth.png',3,'Testuser3'),
('this is tttttttttttt eeeeeeeeeeeeeeee  sssssssssssssssssssss tttttttttttttttttttttttttttt article','test article','Lion.png',1,'Testuser1');

INSERT INTO blogging_comment(text,username, userId, articleId)
VALUES('good','Testuser1',1,1),
       ('nice work','Testuser2',2,1);
