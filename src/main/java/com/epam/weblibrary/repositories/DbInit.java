package com.epam.weblibrary.repositories;

import com.epam.weblibrary.entities.book.Book;
import com.epam.weblibrary.entities.book.BookCover;
import com.epam.weblibrary.entities.book.Genre;
import com.epam.weblibrary.entities.user.Roles;
import com.epam.weblibrary.entities.user.User;
import com.epam.weblibrary.entities.user.UserCredentials;
import com.epam.weblibrary.repositories.book.BookRepository;
import com.epam.weblibrary.repositories.book.CoverRepository;
import com.epam.weblibrary.repositories.user.UserRepository;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Class for database initialization
 * Inserts base books, admin and one simple user
 * Configures prices, discounts and book covers (including default cover)
 */
public class DbInit {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoverRepository coverRepository;

    @SneakyThrows
    public void initialize(){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //create users
        UserCredentials userCredentials = new UserCredentials("user", passwordEncoder.encode("123"), Roles.ROLE_USER);
        User simpleUser = new User(userCredentials, "Iurii", "Miedviediev", "druidkuma@gmail.com");
        simpleUser.setDiscount(0.3F);

        UserCredentials adminCredentials = new UserCredentials("admin", passwordEncoder.encode("admin"), Roles.ROLE_ADMIN);
        User adminUser = new User(adminCredentials, "Admin", "Adminov", "iurii_miedviediev@epam.com");
        adminUser.setDiscount(0.5F);

        userRepository.save(simpleUser);
        userRepository.save(adminUser);

        //create default cover for books
        byte[] defaultCover = IOUtils.toByteArray(getClass().getResourceAsStream("/book-covers/default_book_cover.jpg"));
        coverRepository.save(new BookCover(defaultCover));

        //create Harry Potter books
        String[] titles = new String[] {"Harry Potter and the Philosopher's Stone",
                "Harry Potter and the Chamber of Secrets",
                "Harry Potter and the Prisoner of Azkaban",
                "Harry Potter and the Goblet of Fire",
                "Harry Potter and the Order of the Phoenix",
                "Harry Potter and the Half-Blood Prince",
                "Harry Potter and the Deathly Hallows"};

        String author = "J.K.Rowling";
        Integer[] years = new Integer[] {1997, 1998, 1999, 2000, 2003, 2005, 2007};
        String[] descriptions = new String[] {
                "The plot follows Harry Potter, " +
                        "a young wizard who discovers his magical heritage, as he makes close " +
                        "and a few enemies in his first year at the Hogwarts School of Witchcraft and Wizardry. " +
                        "With the help of his friends, Harry faces an attempted comeback by the dark wizard " +
                        "Lord Voldemort, who killed Harry's parents, but failed to kill Harry when he was just " +
                        "one year old.",
                "The plot follows Harry's second year at Hogwarts School of Witchcraft and Wizardry, " +
                        "during which a series of messages on the walls of the school's corridors warn " +
                        "that the 'Chamber of Secrets' has been opened and that the 'heir of Slytherin' " +
                        "would kill all pupils who do not come from all-magical families. These threats are " +
                        "followed by attacks which leave residents of the school 'petrified' (frozen like " +
                        "stone). Throughout the year, Harry and his friends Ron Weasley and Hermione Granger " +
                        "investigate the attacks.",
                "The book follows Harry Potter, a young wizard, in his third year at Hogwarts School of Witchcraft " +
                        "and Wizardry. Along with friends Ron Weasley and Hermione Granger, Harry investigates " +
                        "Sirius Black, an escaped prisoner from Azkaban whom they believe is one of Lord Voldemort's " +
                        "old allies.",
                "Harry Potter and the Goblet of Fire is the fourth novel in the Harry Potter series, " +
                        "written by British author J. K. Rowling. It follows Harry Potter, a wizard in his " +
                        "fourth year at Hogwarts School of Witchcraft and Wizardry and the mystery " +
                        "surrounding the entry of Harry's name into the Triwizard Tournament, in which he " +
                        "is forced to compete.",
                "It follows Harry Potter's struggles through his fifth year at Hogwarts School of Witchcraft " +
                        "and Wizardry, including the surreptitious return of the antagonist Lord Voldemort, " +
                        "O.W.L. exams, and an obstructive Ministry of Magic.",
                "Set during protagonist Harry Potter's sixth year at Hogwarts, the novel explores the past of " +
                        "Harry's nemesis, Lord Voldemort, and Harry's preparations for the final battle alongside " +
                        "his headmaster and mentor Albus Dumbledore.",
                "Major themes in the novel are death and living in a corrupted society, and critics have " +
                        "compared them to Christian allegories. Generally well-received, the book won the " +
                        "2008 Colorado Blue Spruce Book Award, and the American Library Association named it " +
                        "a \"Best Book for Young Adults\". A two-part film adaptation began showing in " +
                        "November 2010 when Harry Potter and the Deathly Hallows Part 1 was released; " +
                        "Part 2 was released on 15 July 2011."};

        for(int j = 0; j < 3; j++) {
            for(int i = 0; i < 7; i++) {
                byte[] bFile = IOUtils.toByteArray(getClass().getResourceAsStream("/book-covers/harrypotter/hp_" + (i+1) + ".jpg"));

                Book book = new Book(titles[i], author, years[i], descriptions[i], 99L, Genre.Fantasy);
                book.setCover(new BookCover(bFile));
                bookRepository.save(book);
            }
        }

        //Create different books
        String[] title = new String[] {"Doctor Sleep", "The Skinny Taste CookBook", "Alchemist", "The Blood of the Olympus", "Playing It Safe",
                "Hello Love", "The Long Way Home", "Bone River", "Love Sense", "The Ghost Files", "Detective",
                "Beginning Programming For Dummies", "The Novel: A Biography"};

        String[] authors = new String[] {"Stephen King", "Gina Homolka", "Paulo Coelho", "Rick Riordan", "Barbie Bohrman", "Karen McQuestion",
                "Karen McQuestion", "Megan Chance", "Sue Johnson", "Apryl Baker", "Arthur Hailey", "Wallace Wang", "Michael Schmidt"};
        Integer[] year = new Integer[] {2013, 2014, 2006, 2014, 2014, 2012, 2011, 2010, 2005, 2007, 1997, 2008, 2014};
        Genre[] genres = new Genre[] {Genre.Fiction, Genre.Cooking, Genre.Fiction, Genre.Adventure, Genre.Romantic, Genre.Romantic,
                Genre.Romantic, Genre.Romantic, Genre.Romantic, Genre.Horror, Genre.Detective, Genre.IT, Genre.Novel};
        String[] description = new String[] {
                "Stephen King returns to the character and territory of one of his most popular novels ever, " +
                        "The Shining, in this instantly riveting novel about the now middle-aged Dan Torrance and the " +
                        "very special twelve-year-old girl he must save from a tribe of murderous paranormals.",
                "Get the recipes everyone is talking about in the debut cookbook from the wildly popular blog Skinnytaste",
                "The Alchemist by Paulo Coelho continues to change the lives of its readers forever. With more than two " +
                        "million copies sold around the world, The Alchemist has established itself as a modern classic, " +
                        "universally admired. Paulo Coelho's masterpiece tells the magical story of Santiago, " +
                        "an Andalusian shepherd boy who yearns to travel in search of a worldly treasure as " +
                        "extravagant as any ever found.",
                "Though the Greek and Roman crewmembers of the Argo II have made progress in their many quests," +
                        " they still seem no closer to defeating the earth mother, Gaea. Her giants have risen-all of " +
                        "them-and they're stronger than ever. They must be stopped before the Feast of Spes, when Gaea " +
                        "plans to have two demigods sacrificed in Athens. She needs their blood-the blood of " +
                        "Olympus-in order to wake. ",
                "Julia Boyd has dated more than her share of jerks, and sh done even pretending to like them. " +
                        "Putting her dating life on hold and focusing on her event planning career is a much safer bet " +
                        "for the blue-eyed blonde she meets her newest client. Organizing the opening for an " +
                        "art gallery should be a breeze for Julia, but gallery owner Alex Holt is becoming very " +
                        "distracting. Tall and muscular with enough charisma and sexy dimples to make Julia swoon, " +
                        "Alex could be the perfect man. The two of them click immediately, " +
                        "creating enough witty banter to ignite sparks.",
                "A year after the death of his wife, Christine, Dan is barely holding on. But one thing gets him " +
                        "through the long, lonely nights and that is his cherished dog, Anni. When she is stolen " +
                        "from his front yard, Dan and his daughter, Lindsay, are devastated. Meanwhile in another " +
                        "part of town, Andrea Keller is recovering from the heartbreak of a messy divorce. After she " +
                        "rescues a defenseless dog from an abusive tenant, her life changes in ways she never could " +
                        "have anticipated.",
                "For Wisconsinites Marnie, Laverne and Rita, life isn't working out so well. Each is biding time, " +
                        "waiting for something better, something to transport them out of what their lives have " +
                        "recently become. And then there's Jazzy: bubbly, positive, and happy even though she hears " +
                        "voices of the departed. Brought together by a chance meeting, the women decide to join Marnie " +
                        "on a road trip from Wisconsin to Las Vegas where she intends to reunite with Troy, the boy " +
                        "anticipated. Humorous, heartwarming, and bittersweet, the journey has something special in " +
                        "store for each woman.",
                "On her 37th birthday, Leonie discovers a mummy protruding from the riverbank bordering her " +
                        "property--a mummy that by all evidence should exist. As Leonie searches for answers to " +
                        "a native elder insists that Leonie wear a special shell bracelet for protection. " +
                        "But protection from whom? The mummy? Or perhaps Daniel?",
                "Every day, we hear of relationships failing and questions of whether humans are meant to be " +
                        "monogamous. LOVE SENSE presents new scientific evidence that tells us that humans are " +
                        "meant to mate for life. Dr. Johnson explains that romantic love is an attachment bond, " +
                        "just like that between mother and child, and shows us how to develop our \"love sense\"--our " +
                        "ability to develop long-lasting relationships.",
                "For sixteen year old Mattie Hathaway, this is her normal everyday routine. She's been able to " +
                        "see ghosts since her mother tried to murder her when she was five years old. No way " +
                        "does she want anyone to know she can talk to spooks. Being a foster kid is hard enough " +
                        "without being labeled a freak too.",
                "Hours before he is due to set off on a long-delayed and much-deserved vacation with his wife and " +
                        "son, Det.-Sgt. Malcolm Ainslie takes a phone call he would have been better off ignoring. " +
                        "The caller is the chaplain at Florida State Prison, delivering a message from Elroy Doil, " +
                        "the serial murderer Ainslie helped put on the prison's death row.",
                "So you want to be a programmer? Or maybe you just want to be able to make your computer do what " +
                        "YOU want for a change? Maybe you enjoy the challenge of identifying a problem and solving it. " +
                        "If programming intrigues you for whatever reason, Beginning Programming All-In-One Desk " +
                        "Reference For Dummies is like having a starter programming library all in " +
                        "one handy, if beefy, book.",
                "The 700-year history of the novel in English defies straightforward telling. " +
                        "Geographically and culturally boundless, with contributions from Great Britain, " +
                        "Ireland, America, Canada, Australia, India, the Caribbean, and Southern Africa"};

        Long[] prices = new Long[] {199L, 139L, 99L, 99L, 199L, 299L, 199L, 99L, 399L, 99L, 99L, 1999L, 99L};


        for(int q = 0; q < 3; q++) {
            for(int k = 0; k < title.length; k++) {
                byte[] cover = IOUtils.toByteArray(getClass().getResourceAsStream("/book-covers/other/" + k + ".jpg"));

                Book book = new Book(title[k], authors[k], year[k], description[k], prices[k], genres[k]);
                book.setCover(new BookCover(cover));

                bookRepository.save(book);
            }
        }
    }
}