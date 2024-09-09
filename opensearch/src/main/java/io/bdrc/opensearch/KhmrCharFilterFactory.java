package io.bdrc.opensearch;

import java.io.Reader;

import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractCharFilterFactory;
import org.opensearch.index.analysis.CharFilterFactory;

import io.bdrc.lucene.km.NormalizationCharFilter;

public class KhmrCharFilterFactory extends AbstractCharFilterFactory implements CharFilterFactory {
    
    private int lenient_level = 0;
    
    public KhmrCharFilterFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(indexSettings, name);
        this.lenient_level = settings.getAsInt("lenient_level", 0);
    }

    @Override
    public Reader create(Reader reader) {
        reader = new NormalizationCharFilter(reader, this.lenient_level);
        return reader;
    }

}
