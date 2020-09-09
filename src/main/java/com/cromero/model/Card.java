package com.cromero.model;

//import com.cromero.analyzer.SynonymFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.synonym.SynonymFilterFactory;
import org.hibernate.search.annotations.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Indexed
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Field() //index = Index.YES, analyze = Analyze.YES, store = Store.NO
    @AnalyzerDef(name = "edgeNgram",
            tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class),
            filters = {
                    @TokenFilterDef(factory = ASCIIFoldingFilterFactory.class), // Replace accented characeters by their simpler counterpart (è => e, etc.)
                    @TokenFilterDef(factory = LowerCaseFilterFactory.class), // Lowercase all characters
                    @TokenFilterDef(
                            factory = EdgeNGramFilterFactory.class, // Generate prefix tokens
                            params = {
                                    @Parameter(name = "minGramSize", value = "2"),
                                    @Parameter(name = "maxGramSize", value = "4")
                            }
                    ),
                    @TokenFilterDef(
                            factory = SynonymFilterFactory.class,
                            params = {
                                    @Parameter(name = "ignoreCase", value = "true"),
                                    @Parameter(name = "expand", value = "true"),
                                    @Parameter(name = "synonyms", value = "src/main/resources/synonyms.txt"),
                            })

            })


    private String holderName;
    @Field() //index = Index.YES, analyze = Analyze.YES, store = Store.NO
    @AnalyzerDef(name = "edgeNgram_1",
            tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class),
            filters = {
                    @TokenFilterDef(factory = ASCIIFoldingFilterFactory.class), // Replace accented characeters by their simpler counterpart (è => e, etc.)
                    @TokenFilterDef(factory = LowerCaseFilterFactory.class), // Lowercase all characters
                    @TokenFilterDef(
                            factory = EdgeNGramFilterFactory.class, // Generate prefix tokens
                            params = {
                                    @Parameter(name = "minGramSize", value = "2"),
                                    @Parameter(name = "maxGramSize", value = "4")
                            }
                    )
            })
    private String holderSurname;

    @Field() //index = Index.YES, analyze = Analyze.YES, store = Store.NO
    @AnalyzerDef(name = "edgeNgram_2",
            tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class),
            filters = {
                    @TokenFilterDef(factory = ASCIIFoldingFilterFactory.class), // Replace accented characeters by their simpler counterpart (è => e, etc.)
                    @TokenFilterDef(factory = LowerCaseFilterFactory.class), // Lowercase all characters
                    @TokenFilterDef(
                            factory = EdgeNGramFilterFactory.class, // Generate prefix tokens
                            params = {
                                    @Parameter(name = "minGramSize", value = "2"),
                                    @Parameter(name = "maxGramSize", value = "4")
                            }
                    )
            })
    private String expiration;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getHolderSurname() {
        return holderSurname;
    }

    public void setHolderSurname(String holderSurname) {
        this.holderSurname = holderSurname;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
