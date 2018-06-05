package Wordslist;

public class Wordslist {


        public String mWordslist[]={
                "TRANSFORMERS" ,
                "ALMOND",
                "VOLKSWAGEN",
                "ENGINEER",
                "AMSTERDAM",
                "PAPRIKA",
                "STARBUCKS",
                "DHONI",
                "SEOUL",
                "ABERRATION",
                "CONTENTIOUS",
                "INSTAGRAM",
                "STRAWBERRY",
                "MARSHMELLOW",
                "PASSWORD",
                "PLYWOOD",
                "BARCELONA",
                "BAKELITE",
                "PIZZA",
                "RATATOUILLE"

        };


        public String mDesc[]={"Movie","Nut","Brand","Profession","Place","Spice","Coffee","Personality",
                "Place","Dictionary","Dictionary","Application","Fruit","6.0","Login","Material","Spain","Material",
                "Food","Movie"};

        public String getWords(int a) {
            String Stone=mWordslist[a];
            return Stone;

        }

        public String getitems(int a) {
            String Items=mDesc[a];
            return Items;

        }



}
