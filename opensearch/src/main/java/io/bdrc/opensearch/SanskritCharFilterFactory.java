package io.bdrc.opensearch;

import java.io.Reader;

import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractCharFilterFactory;
import org.opensearch.index.analysis.CharFilterFactory;

import io.bdrc.lucene.sa.AnusvaraNormalizer;
import io.bdrc.lucene.sa.Deva2SlpFilter;
import io.bdrc.lucene.sa.GeminateNormalizingFilter;
import io.bdrc.lucene.sa.LenientCharFilter;
import io.bdrc.lucene.sa.Roman2SlpFilter;

public class SanskritCharFilterFactory extends AbstractCharFilterFactory implements CharFilterFactory {

    private Boolean lenient = false;
    private String inputMethod = null;
    private Boolean filterGeminates = false;
    private Boolean normalizeAnusvara = false;
    
    public SanskritCharFilterFactory(final IndexSettings indexSettings, final Environment env, final String name, final Settings settings) {
        super(indexSettings, name);
        this.lenient = settings.getAsBoolean("lenient", false);
        this.normalizeAnusvara = settings.getAsBoolean("normalize_anusvara", false);
        this.filterGeminates = settings.getAsBoolean("filter_geminates", false);
        this.inputMethod = settings.get("input_method", null); // `SLP`, `deva` or `roman`
    }

    @Override
    public Reader create(Reader reader) {
        if ("deva".equals(this.inputMethod)) {
            reader = new Deva2SlpFilter(reader);
        } else if ("roman".equals(this.inputMethod)) {
            reader = new Roman2SlpFilter(reader);
        }
        if (this.filterGeminates)
            reader = new GeminateNormalizingFilter(reader);
        
        if (this.normalizeAnusvara)
            reader = new AnusvaraNormalizer(reader);
        
        // what happens in lenient mode is that first the input is transformed
        // into SLP then into SLP->Lenient. This is a bit awkward but it should work
        if (this.lenient != null) {
            reader = new LenientCharFilter(reader);
        }
        return reader;
    }

}
