package com.example.threewaymerge.data;

import java.util.Map;

public class EntryProvider {

    public static BibEntry getLeftEntry() {
        return new BibEntry(
                Map.of("Entry type", "InProceedings",
                        "Author", "Alexander J. DeWitt and Jasna Kuljis",
                        "Abstract", """
                                tempora. Sit laborum ab, eius fugit doloribus tenetur
                                fugiat, temporibus enim commodi iusto libero magni deleniti quod quam
                                consequuntur! Commodi minima excepturi repudiandae velit hic maxime
                                doloremque. Quaerat provident commodi consectetur veniam similique ad
                                earum omnis ipsum saepe, voluptas, hic voluptates pariatur est explicabo
                                fugiat, dolorum eligendi quam cupiditate excepturi mollitia maiores labore
                                suscipit quas? Nulla, placeat. Voluptatem quaerat non architecto ab laudantium
                                modi minima sunt esse temporibus sint culpa, recusandae
                                """,
                        "Booktitle", "SOUPS '06: Proceedings of the second symposium on Usable privacy and security",
                        "Doi", "10.1145/1143120.1143122",
                        "Isbn", "1-59593-448-0",
                        "Location", "Pittsburgh, Pennsylvania",
                        "Year", "2006",
                        "Number", "3",
                        "Pages", "10--12")
        );
    }

    public static BibEntry getRightEntry() {
        return new BibEntry(
                Map.of("Entry type", "Article",
                        "Author", "Houssem J. DeWitt and Jasna Kuljis",
                        "Abstract", """
                                 ut molestias architecto voluptate aliquam
                                nihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit,
                                """,
                        "Booktitle", "SOUPS '06: Article of the second symposium on Usable privacy and security",
                        "Doi", "10.1145/1143120.1143122",
                        "Isbn", "1-59593-448-0",
                        "Location", "Pittsburgh, Tunisia",
                        "Year", "2012",
                        "Number", "19",
                        "Pages", "11--12")
        );
    }

    public static BibEntry getMergedEntry() {
        return new BibEntry(
                Map.of("Entry type", "InProceedings",
                        "Author", "Alexander J. DeWitt and Jasna Kuljis",
                        "Abstract", """
                                 ut molestias architecto voluptate aliquam
                                nihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit,
                                tenetur error, harum nesciunt. Reprehenderit,
                                quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos
                       
                                """,
                        "Booktitle", "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical\n" +
                                "Latin literature from 45 BC, making it over 2000 years old. Richard McClintock,",
                        "Doi", "10.1145/1143120.1143122",
                        "Isbn", "1-59593-448-0",
                        "Location", "Pittsburgh, Pennsylvania",
                        "Year", "2006",
                        "Number", "3",
                        "Pages", "10--12")
        );
    }
}
