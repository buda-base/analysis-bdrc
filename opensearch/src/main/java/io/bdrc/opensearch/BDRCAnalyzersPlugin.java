package io.bdrc.opensearch;

import org.opensearch.index.analysis.CharFilterFactory;
import org.opensearch.index.analysis.TokenFilterFactory;
import org.opensearch.index.analysis.TokenizerFactory;
import org.opensearch.indices.analysis.AnalysisModule;
import org.opensearch.plugins.AnalysisPlugin;
import org.opensearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class BDRCAnalyzersPlugin extends Plugin implements AnalysisPlugin {

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<CharFilterFactory>> getCharFilters() {
        Map<String, AnalysisModule.AnalysisProvider<CharFilterFactory>> extra = new HashMap<>();
        extra.put("iast", SanskritCharFilterFactory::new);
        extra.put("mymr", MymrCharFilterFactory::new);
        extra.put("khmr", KhmrCharFilterFactory::new);
        return extra;
    }
    
    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {
        Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> extra = new HashMap<>();
        extra.put("iast", SanskritTokenizerFactory::new);
        extra.put("khmr", SanskritTokenizerFactory::new);
        return extra;
    }
    
    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
        Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> extra = new HashMap<>();
        extra.put("khmr", KhmrTokenFilterFactory::new);
        return extra;
    }
}