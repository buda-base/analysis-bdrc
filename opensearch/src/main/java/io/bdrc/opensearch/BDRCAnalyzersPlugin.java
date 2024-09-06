package io.bdrc.opensearch;

import org.apache.lucene.analysis.Analyzer;
import org.opensearch.index.analysis.AnalyzerProvider;
import org.opensearch.index.analysis.CharFilterFactory;
import org.opensearch.index.analysis.TokenFilterFactory;
import org.opensearch.index.analysis.TokenizerFactory;
import org.opensearch.indices.analysis.AnalysisModule;
import org.opensearch.indices.analysis.AnalysisModule.AnalysisProvider;
import org.opensearch.plugins.AnalysisPlugin;
import org.opensearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class BDRCAnalyzersPlugin extends Plugin implements AnalysisPlugin {

    
}