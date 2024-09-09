package io.bdrc.opensearch;

import java.io.Reader;

import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractCharFilterFactory;
import org.opensearch.index.analysis.CharFilterFactory;

import io.bdrc.lucene.mymr.MyanmarCharFilter;
import io.bdrc.lucene.mymr.MyanmarReorderCharFilter;

public class MymrCharFilterFactory  extends AbstractCharFilterFactory implements CharFilterFactory {
    
    private Boolean lenient = false;

    public MymrCharFilterFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(indexSettings, name);
        this.lenient = settings.getAsBoolean("lenient", false);
    }

    @Override
    public Reader create(Reader reader) {
        reader = new MyanmarReorderCharFilter(reader);
        reader = new MyanmarCharFilter(reader, lenient);
        return reader;
    }

    
}
