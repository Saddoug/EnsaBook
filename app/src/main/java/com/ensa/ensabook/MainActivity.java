package com.ensa.ensabook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{
    private Toolbar toolbar;
   private ImageView leftIcon ;

   ImageView rightIcon ;
    ImageView loginIcon ;
   TextView title ;
   List<Model> modelList;
   Model modelFromAddbookActivity=null;
   RecyclerView recyclerView;
   BookAdapter bookAdapter;
   SearchView searchView;
   BottomNavigationView bottomNavigationView;
   DatabaseHelper databaseHelper;
    static boolean firstCreate =true;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper=new DatabaseHelper(getApplicationContext());
        toolbar=findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(MainActivity.this,"HOME",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                return true;
            }
        });
        bottomNavigationView.getMenu().getItem(2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(MainActivity.this,"Favorote Books",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,FavoriteActivity.class);
                startActivity(intent);
                return true;
            }
        });

        leftIcon= findViewById(R.id.left_icon);
        rightIcon= findViewById(R.id.right_icon);
        title=findViewById(R.id.toolbar_title);
        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"EnsaBook",Toast.LENGTH_SHORT).show();

            }
        });
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"About US",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });
        loginIcon= findViewById(R.id.login_icon);
        loginIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });
        //creating an arraylist
       // modelFromAddbookActivity =getIntent().getParcelableExtra("modeladded");
        modelList=new ArrayList<>();
//        if (modelFromAddbookActivity!= null){
//        modelList.add(modelFromAddbookActivity);}
       if (firstCreate){
           databaseHelper.AddBook(new Model(null,"GOODBYE, VITAMIN","BY RACHEL KHONG","FICTION","Her life at a crossroads, a young woman goes home again in this funny and inescapably moving debut from a wonderfully original new literary voice. Freshly disengaged from her fiancé and feeling that life has not turned out quite the way she planned, thirty-year-old Ruth quits her job, leaves town and arrives at her parents’ home to find that situation more complicated than she’d realized. Her father, a prominent history professor, is losing his memory and is only erratically lucid. Ruth’s mother, meanwhile, is lucidly erratic. But as Ruth’s father’s condition intensifies, the comedy in her situation takes hold, gently transforming all her grief. Told in captivating glimpses and drawn from a deep well of insight, humor, and unexp cted tenderness, Goodbye, Vitamin pilots through the loss, love, and absurdity of finding one’s footing in this life.",55.0,false,R.drawable.goodbye,false));

           databaseHelper.AddBook(new Model(null,"CHEMISTRY"," BY WEIKE WANG","ROMANCE","At first glance, the quirky, overworked narrator of Weike Wang’s debut novel seems to be on the cusp of a perfect life: she is studying for a prestigious PhD in chemistry that will make her Chinese parents proud (or at least satisfied), and her successful, supportive boyfriend has just proposed to her. But instead of feeling hopeful, she is wracked with ambivalence: the long, demanding hours at the lab have created an exquisite pressure cooker, and she doesn’t know how to answer the marriage question. When it all becomes too much and her life plan veers off course, she finds herself on a new path of discoveries about everything she thought she knew. Smart, moving, and always funny, this unique coming-of-age story is certain to evoke a winning reaction.",55.0,false,R.drawable.chemistry,false));

           databaseHelper.AddBook(new Model(null,"NUTSHELL","BY IAN MCEWAN","FICTION","Trudy has been unfaithful to her husband, John. What’s more, she has kicked him out of their marital home, a valuable old London town house, and in his place is his own brother, the profoundly banal Claude. The illicit couple have hatched a scheme to rid themselves of her inconvenient husband forever. But there is a witness to their plot: the inquisitive, nine-month-old resident of Trudy’s womb. As Trudy’s unborn son listens, bound within her body, to his mother and his uncle’s murderous plans, he gives us a truly new perspective on our world, seen from the confines of his. McEwan’s brilliant recasting of Shakespeare lends new weight to the age-old question of Hamlet’s hesitation, and is a tour de force of storytelling.",55.0,false,R.drawable.nutshell,false));
           databaseHelper.AddBook(new Model(null,"SKIM ","BY MARIKO AND JILLIAN TAMAKI"," GRAPHIC NOVEL","‘Skim’ is Kimberly Keiko Cameron, a not-slim, would-be Wiccan goth who goes to a private girls’ school in the early ’90s. When her classmate Katie Matthews is dumped by her boyfriend, who then kills himself — possibly because he’s (maybe) gay — the entire school goes into mourning overdrive. It’s a weird time to fall in love, but that’s what happens to Skim when she starts meeting secretly with her neo-hippie English teacher, Ms. Archer. But then Ms. Archer abruptly leaves the school, and Skim has to cope with her confusion and isolation while her best friend, Lisa, tries to pull her into ‘real’ life by setting up a hilarious double-date for the school’s semi formal. Suicide, depression, love, homosexuality, crushes, cliques of popular, manipulative peers — the whole gamut of teen life is explored in this poignant glimpse into the heartache of being 16.",55.0,false,R.drawable.skim,false));
           databaseHelper.AddBook(new Model(null,"Tanqueray ","by Stephanie Johnson and Brandon Stanton","THRILLER","In 2019, Tanqueray, one of the most well-known burlesque dancers in New York City, once again captured the attention of millions when she was featured on Humans of New York.Tanqueray is the captivating story of Stephanie Johnson's experiences in 1970s New York City, including personal photos and stories that weren't included in her <Humans of New York> series. ",55.0,false,R.drawable.tanqueray,false));
           databaseHelper.AddBook(new Model(null,"The Defining Decade Why Your Twenties Matter—And How to Make the Most of Them Now ","by Dr Meg Jay","THRILLER","Dr. Meg Jay is a psychologist who drew from nearly 20 years of work to demonstrate that our 20s are not a second adolescence, but the most defining decade of adulthood. This book argues that our 20s are not to be trivialized, that we change and develop rapidly because of our jobs, relationships, social networks, and evolving identities. In her book, Dr. Jay takes many of the complaints about life in our 20s and offers practical guides to make the most of the 10 years that may define the rest of our lives.",55.0,false,R.drawable.thedefiningdecade,false));
           databaseHelper.AddBook(new Model(null,"THE MARROW THIEVES  ","BY CHERIE DIMALINE"," FICTION","Humanity has nearly destroyed its world through global warming, but now an even greater evil lurks. The indigenous people of North America are being hunted and harvested for their bone marrow, which carries the key to recovering something the rest of the population has lost: the ability to dream. In this dark world, Frenchie and his companions struggle to survive as they make their way up north to the old lands. For now, survival means staying hidden—but what they don’t know is that one of them holds the secret to defeating the marrow thieves.",55.0,false,R.drawable.themarrow,false));
           databaseHelper.AddBook(new Model(null,"WOMEN & POWER A MANIFESTO","BY MARY BEARD ","NONFICTION","Britain’s best-known classicist Mary Beard, is also a committed and vocal feminist. With wry wit, she revisits the gender agenda and shows how history has treated powerful women. Her examples range from the classical world to the modern day, from Medusa and Athena to Theresa May and Hillary Clinton. Beard explores the cultural underpinnings of misogyny, considering the public voice of women, our cultural assumptions about women’s relationship with power, and how powerful women resist being packaged into a male template. With personal reflections on her own experiences of the sexism and gendered aggression she has endured online, Mary asks: if women aren’t perceived to be within the structures of power, isn’t it power that we need to redefine?",55.0,false,R.drawable.womenpower,false));
           databaseHelper.AddBook(new Model(null,"And Then There Were None"," by Agatha Christie","THRILLER","In each room of the mansion where 10 strangers are gathered, there hangs a famous nursery rhyme, describing 10 people dwindinglng down to none. When the guests realize they're being murdered as described in the rhyme, they have to figure out who is orchestrating it all and why, before there are none of them left. Agatha Christie is an iconic murder mystery novelist and if you haven't read one of her books, this is the perfect place to start. It's an intense <whodunnit> that's fun to read as you gather clues to solve the puzzle before you reach the final page.",55.0,false,R.drawable.andthen,false));
           databaseHelper.AddBook(new Model(null,"Elevation ","by Stephen King"," FICTION","Known for his long and horrifying novels, Stephen King manages to pack his signature creepy storytelling into his second-shortest novel, Elevation. In the town of Castle Rock, Scott Carey has been steadily losing weight (and experiencing a couple of other odd things) but doesn't want to be poked and prodded by doctors. He's also engaged in a mini-battle with his neighbors — a lesbian couple whose dog keeps pooping on his lawn. As Scott begins to understand the prejudice the women face, an alliance forms. This is a refreshing and reinvigorating novel, one that draws you in with Stephen King's style and keeps you hooked with his signature twists.",55.0,false,R.drawable.elevation,false));
           databaseHelper.AddBook(new Model(null,"title1","author1","category1","description1",55.0,false,R.drawable.cover1,false));
           databaseHelper.AddBook(new Model(null,"title2","author2","category2","description2",55.0,false,R.drawable.cover2,false));
           databaseHelper.AddBook(new Model(null,"title3","author3","category3","description3",55.0,false,R.drawable.cover3,false));
           databaseHelper.AddBook(new Model(null,"title4","author4","category4","description4",55.0,false,R.drawable.cover4,false));
           databaseHelper.AddBook(new Model(null,"title5","author5","category5","description5",55.0,false,R.drawable.cover5,false));
            firstCreate=false;
       }
//        modelList.add(new Model("GOODBYE, VITAMIN","BY RACHEL KHONG","FICTION","Her life at a crossroads, a young woman goes home again in this funny and inescapably moving debut from a wonderfully original new literary voice. Freshly disengaged from her fiancé and feeling that life has not turned out quite the way she planned, thirty-year-old Ruth quits her job, leaves town and arrives at her parents’ home to find that situation more complicated than she’d realized. Her father, a prominent history professor, is losing his memory and is only erratically lucid. Ruth’s mother, meanwhile, is lucidly erratic. But as Ruth’s father’s condition intensifies, the comedy in her situation takes hold, gently transforming all her grief. Told in captivating glimpses and drawn from a deep well of insight, humor, and unexp cted tenderness, Goodbye, Vitamin pilots through the loss, love, and absurdity of finding one’s footing in this life.",55.0,false,R.drawable.goodbye));
//        modelList.add(new Model("NUTSHELL","BY IAN MCEWAN","FICTION","Trudy has been unfaithful to her husband, John. What’s more, she has kicked him out of their marital home, a valuable old London town house, and in his place is his own brother, the profoundly banal Claude. The illicit couple have hatched a scheme to rid themselves of her inconvenient husband forever. But there is a witness to their plot: the inquisitive, nine-month-old resident of Trudy’s womb. As Trudy’s unborn son listens, bound within her body, to his mother and his uncle’s murderous plans, he gives us a truly new perspective on our world, seen from the confines of his. McEwan’s brilliant recasting of Shakespeare lends new weight to the age-old question of Hamlet’s hesitation, and is a tour de force of storytelling.",55.0,false,R.drawable.nutshell));
//        modelList.add(new Model("SKIM ","BY MARIKO AND JILLIAN TAMAKI"," GRAPHIC NOVEL","‘Skim’ is Kimberly Keiko Cameron, a not-slim, would-be Wiccan goth who goes to a private girls’ school in the early ’90s. When her classmate Katie Matthews is dumped by her boyfriend, who then kills himself — possibly because he’s (maybe) gay — the entire school goes into mourning overdrive. It’s a weird time to fall in love, but that’s what happens to Skim when she starts meeting secretly with her neo-hippie English teacher, Ms. Archer. But then Ms. Archer abruptly leaves the school, and Skim has to cope with her confusion and isolation while her best friend, Lisa, tries to pull her into ‘real’ life by setting up a hilarious double-date for the school’s semi formal. Suicide, depression, love, homosexuality, crushes, cliques of popular, manipulative peers — the whole gamut of teen life is explored in this poignant glimpse into the heartache of being 16.",55.0,false,R.drawable.skim));
//        modelList.add(new Model("Tanqueray ","by Stephanie Johnson and Brandon Stanton","THRILLER","In 2019, Tanqueray, one of the most well-known burlesque dancers in New York City, once again captured the attention of millions when she was featured on Humans of New York.Tanqueray is the captivating story of Stephanie Johnson's experiences in 1970s New York City, including personal photos and stories that weren't included in her <Humans of New York> series. ",55.0,false,R.drawable.tanqueray));
//        modelList.add(new Model("The Defining Decade Why Your Twenties Matter—And How to Make the Most of Them Now ","by Dr Meg Jay","THRILLER","Dr. Meg Jay is a psychologist who drew from nearly 20 years of work to demonstrate that our 20s are not a second adolescence, but the most defining decade of adulthood. This book argues that our 20s are not to be trivialized, that we change and develop rapidly because of our jobs, relationships, social networks, and evolving identities. In her book, Dr. Jay takes many of the complaints about life in our 20s and offers practical guides to make the most of the 10 years that may define the rest of our lives.",55.0,false,R.drawable.thedefiningdecade));
//        modelList.add(new Model("THE MARROW THIEVES  ","BY CHERIE DIMALINE"," FICTION","Humanity has nearly destroyed its world through global warming, but now an even greater evil lurks. The indigenous people of North America are being hunted and harvested for their bone marrow, which carries the key to recovering something the rest of the population has lost: the ability to dream. In this dark world, Frenchie and his companions struggle to survive as they make their way up north to the old lands. For now, survival means staying hidden—but what they don’t know is that one of them holds the secret to defeating the marrow thieves.",55.0,false,R.drawable.themarrow));
//        modelList.add(new Model("WOMEN & POWER A MANIFESTO","BY MARY BEARD ","NONFICTION","Britain’s best-known classicist Mary Beard, is also a committed and vocal feminist. With wry wit, she revisits the gender agenda and shows how history has treated powerful women. Her examples range from the classical world to the modern day, from Medusa and Athena to Theresa May and Hillary Clinton. Beard explores the cultural underpinnings of misogyny, considering the public voice of women, our cultural assumptions about women’s relationship with power, and how powerful women resist being packaged into a male template. With personal reflections on her own experiences of the sexism and gendered aggression she has endured online, Mary asks: if women aren’t perceived to be within the structures of power, isn’t it power that we need to redefine?",55.0,false,R.drawable.womenpower));
//        modelList.add(new Model("And Then There Were None"," by Agatha Christie","THRILLER","In each room of the mansion where 10 strangers are gathered, there hangs a famous nursery rhyme, describing 10 people dwindinglng down to none. When the guests realize they're being murdered as described in the rhyme, they have to figure out who is orchestrating it all and why, before there are none of them left. Agatha Christie is an iconic murder mystery novelist and if you haven't read one of her books, this is the perfect place to start. It's an intense <whodunnit> that's fun to read as you gather clues to solve the puzzle before you reach the final page.",55.0,false,R.drawable.andthen));
//        modelList.add(new Model("Elevation ","by Stephen King"," FICTION","Known for his long and horrifying novels, Stephen King manages to pack his signature creepy storytelling into his second-shortest novel, Elevation. In the town of Castle Rock, Scott Carey has been steadily losing weight (and experiencing a couple of other odd things) but doesn't want to be poked and prodded by doctors. He's also engaged in a mini-battle with his neighbors — a lesbian couple whose dog keeps pooping on his lawn. As Scott begins to understand the prejudice the women face, an alliance forms. This is a refreshing and reinvigorating novel, one that draws you in with Stephen King's style and keeps you hooked with his signature twists.",55.0,false,R.drawable.elevation));
//        modelList.add(new Model("title1","author1","category1","description1",55.0,false,R.drawable.cover1));
//        modelList.add(new Model("title2","author2","category2","description2",55.0,false,R.drawable.cover2));
//        modelList.add(new Model("title3","author3","category3","description3",55.0,false,R.drawable.cover3));
//        modelList.add(new Model("title4","author4","category4","description4",55.0,false,R.drawable.cover4));
//        modelList.add(new Model("title5","author5","category5","description5",55.0,false,R.drawable.cover5));
      modelList.addAll(databaseHelper.readAllBooks());
// recyclerView
        recyclerView= findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        //adapter
        bookAdapter = new BookAdapter(this,modelList,this);
        recyclerView.setAdapter(bookAdapter);
        //search view
        searchView=findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });



    }

    private void filterList(String text) {
        List<Model> filteredList =new ArrayList<>();
        for(Model model: modelList){
            if(model.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(model);
            }else {
                if(model.getAuthor().toLowerCase().contains(text.toLowerCase())){
                    filteredList.add(model);
                }else {

                    if(model.getCategory().toLowerCase().contains(text.toLowerCase())){
                        filteredList.add(model);
                    }
                }
            }


        }
        if(filteredList.isEmpty()){
            Toast.makeText(this,"NO DATA FOUND",Toast.LENGTH_SHORT).show();
        }else {
            bookAdapter.setFiltredList(filteredList);
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
//        intent.putExtra("title",modelList.get(position).getTitle());
//        intent.putExtra("price",modelList.get(position).getPrice());
//        intent.putExtra("category",modelList.get(position).getCategory());
//        intent.putExtra("author",modelList.get(position).getAuthor());
//        intent.putExtra("description",modelList.get(position).getDescription());
//        intent.putExtra("image",modelList.get(position).getBookPhoto());
        intent.putExtra("model",modelList.get(position));


        startActivity(intent);

    }




    public void favoritism(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
        startActivity(intent);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menusearch,menu);
//        MenuItem menuItem= menu.findItem(R.id.action_search);
//        SearchView searchView=(SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Type here to search");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }

}