import java.util.*;

class Main {
    static void main(String[] args) {
        book book1=new book("Atomic Habits","games cleer","1-652-0-156");
        book book2=new book("the dad i hate","emad rashad","1-981-0-120");
        book book3=new book("Crime and Punishment","Dostoevsky ","3-024-1-688");
       //System.out.println( book1.GetInfo());
       Member member=new Member("Abdallah Soliman","1000287901");
       Member member2=new Member("Nour Abdelmqsoud","1289021343");
       Member member3=new Member("Abdelrhman eldeeb","1906845744");
//        System.out.println(member.GetInfo());
//        member.BorrowBook(book1);
//        member.BorrowBook(book2);
//        member.BorrowBook(book3);
//
          Library lib=new Library("Library of Alex");
          lib.Addbook(book1);
          lib.Addbook(book2);
          lib.Addbook(book3);
          lib.registerMember(member);
          lib.registerMember(member2);
          lib.registerMember(member3);

          //lib.displayAvailableBooks();
            lib.lendBook(member,"324");
            lib.lendBook(member,"1-981-0-120");
            lib.receiveBook(member,"1-981-0-120");

    }
}