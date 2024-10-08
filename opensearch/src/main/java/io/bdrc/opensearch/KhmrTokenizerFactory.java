package io.bdrc.opensearch;

import org.apache.lucene.analysis.Tokenizer;
import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractTokenizerFactory;

import io.bdrc.lucene.km.GraphemeClusterTokenizer;

public class KhmrTokenizerFactory extends AbstractTokenizerFactory {

    public KhmrTokenizerFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(indexSettings, settings, name);
    }

    @Override
    public Tokenizer create() {
        return new GraphemeClusterTokenizer();
    }
    
}
