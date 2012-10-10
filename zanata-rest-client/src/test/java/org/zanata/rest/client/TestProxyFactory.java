package org.zanata.rest.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.jboss.resteasy.client.ClientExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zanata.rest.dto.VersionInfo;


public class TestProxyFactory extends ZanataProxyFactory
{
   private static final Logger log = LoggerFactory.getLogger(TestProxyFactory.class);
   private static final String AUTH_KEY = "b6d7044e9ee3b2447c28fb7c50d86d98";
   private static final String USERNAME = "admin";

   public TestProxyFactory(URI base, String username, String apiKey, ClientExecutor executor, VersionInfo ver)
   {
      super(base, username, apiKey, executor, ver, false);
   }

   public TestProxyFactory(ClientExecutor executor) throws URISyntaxException
   {
      this(new URI("http://example.com/"), USERNAME, AUTH_KEY, executor, new VersionInfo("SNAPSHOT", ""));
   }

   public IProjectIterationResource getProjectIteration(final URI uri)
   {
      return createProxy(IProjectIterationResource.class, uri);
   }

   @Override
   public ITranslatedDocResource getTranslatedDocResource(String projectSlug, String versionSlug)
   {
      try
      {
         log.debug("create proxy for ITranslatedDocResource");
         return createProxy(ITranslatedDocResource.class, new URI("/restv1/projects/p/" + projectSlug + "/iterations/i/" + versionSlug + "/r"));
      }
      catch (URISyntaxException e)
      {
         log.debug("exception:" + e.getMessage());
         throw new RuntimeException(e);
      }
   }

   @Override
   public ISourceDocResource getSourceDocResource(String projectSlug, String versionSlug)
   {
      try
      {
         log.debug("create proxy for ISourceDocResource");
         return createProxy(ISourceDocResource.class, new URI("/restv1/projects/p/" + projectSlug + "/iterations/i/" + versionSlug + "/r"));
      }
      catch (URISyntaxException e)
      {
         log.debug("exception:" + e.getMessage());
         throw new RuntimeException(e);
      }
   }

   @Override
   public IVersionResource createIVersionResource()
   {
      try
      {
         return createProxy(IVersionResource.class, new URI("/restv1/version"));
      }
      catch (URISyntaxException e)
      {
         log.debug("exception:" + e.getMessage());
         throw new RuntimeException(e);
      }
   }

   // FIXME added to allow compilation, may cause test failure
   @Override
   public IGlossaryResource getGlossaryResource()
   {
      try
      {
         return createProxy(IGlossaryResource.class, new URI("/restv1/glossary"));
      }
      catch (URISyntaxException e)
      {
         log.debug("exception:" + e.getMessage());
         throw new RuntimeException(e);
      }
   }

}
