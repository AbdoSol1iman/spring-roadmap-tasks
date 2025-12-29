import java.awt.print.Book;
import java.util.*;
public class Library {
    ///atributes and constructor
    private String name;
    private  ArrayList<book> ExistedBooks=new ArrayList<>();
    private ArrayList<Member> RegisteredMembers=new ArrayList<>();
    Library(String name){
        this.name=name;
    }
    ///getters and setters
    String getName() {
        return name;
    }
    void setName(String name) {
        this.name = name;
    }
    void  displayAvailableBooks() {
        System.out.println("Existing books in "+this.name+" : ");
        short counter=1;
         for(book b:this.ExistedBooks ){
             if(b.isAvailable()) {
                 System.out.print(counter + ".");
                 System.out.println(b.GetInfo());
                 counter++;
             }
        }

    }
    ///private methods
//    private boolean IsExistBook(Member member,String isbn){
//        for(book book1:this.ExistedBooks){
//            if(book1.getIsbn().equals(isbn)){
//                return true;
//            }
//        }
//        return false;
//    }
/// Searching for a book
    private book GetBookByisbn(String isbn){
        for(book book1:this.ExistedBooks){
            if(book1.getIsbn().equals(isbn))
                return book1;
        }
        //return new book("","","",false);\
        return null;
    }
    private book GetBookByTitle(String title){
        for(book book1:this.ExistedBooks){
            if(book1.getTitle().equals(title))
                return book1;
        }
        //return new book("","","",false);\
        return null;
    }
    private book GetBookByAuthor(String author){
        for(book book1:this.ExistedBooks){
            if(book1.getAuthor().equals(author))
                return book1;
        }
        //return new book("","","",false);\
        return null;
    }

    /// Public methods
    void lendBook(Member member,String isbn){
        if(RegisteredMembers.contains(member)) {
            book book1 = GetBookByisbn(isbn);
            if (book1 != null) {
                if (book1.isAvailable()) {
                    book1.borrow();
                    System.out.println("here is your book🥰 :-"+book1.GetInfo());

                } else {
                    System.out.println("Sorry,the book is lended already😭 ");
                }
            } else {
                System.out.println("Sorry,the book is not exist.😭");
            }

        }else System.out.println("You cannot lend, make sure you register first😁");


    }

    void receiveBook(Member member,String isbn){
        if(RegisteredMembers.contains(member)) {
            book book1 = GetBookByisbn(isbn);
            if (book1 != null) {
                if (!book1.isAvailable()) {
                    book1.returnBook();
                    System.out.println("thanks for returning the book🥰");
                }
            } else {
                System.out.println("Sorry, this book is not ours😭 ");
            }

        }else System.out.println("You cannot Return, make sure you register first😁");


    }


    void  Addbook(book book1){
        if(ExistedBooks.contains(book1)){
            System.out.println("Book "+book1.GetInfo()+" already exists.");
        }else{
            ExistedBooks.add(book1);
        }
    }
    void registerMember(Member member){
        if(RegisteredMembers.contains(member)){
            System.out.println("Member : "+member.GetInfo()+" already exists.");
        }else{
            RegisteredMembers.add(member);
        }
    }


}
