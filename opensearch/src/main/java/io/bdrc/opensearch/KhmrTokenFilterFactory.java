package io.bdrc.opensearch;

import org.apache.lucene.analysis.TokenStream;
import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractTokenFilterFactory;

import io.bdrc.lucene.km.CharReorderFilter;

public class KhmrTokenFilterFactory  extends AbstractTokenFilterFactory {
    
    public KhmrTokenFilterFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(indexSettings, name, settings);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        tokenStream = new CharReorderFilter(tokenStream);
        return tokenStream;
    }

}
